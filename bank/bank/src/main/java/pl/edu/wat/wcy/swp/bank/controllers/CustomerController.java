package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wat.wcy.swp.bank.dtos.TransactionDTO;
import pl.edu.wat.wcy.swp.bank.entities.*;
import pl.edu.wat.wcy.swp.bank.repositories.*;
import pl.edu.wat.wcy.swp.bank.services.interfaces.CustomerService;

import java.util.*;
import java.util.logging.Logger;

/**
 * Created by Konrad on 2015-01-03.
 */
@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountTypesRepository bankAccountTypesRepository;

    @RequestMapping("/hello")
    public ModelAndView hello(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        String str = "controller";
        mav.addObject("str",str);

        return mav;
    }

    @RequestMapping("/all")
    public String customers(Model m){

        List<Customer> customers =  customerService.findAll();
        StringBuilder sb = new StringBuilder();
        for (Customer c : customers){
            sb.append(c.getCustomerId()).append("_");
        }

        Customer cc = new Customer();
        cc.setPIN(randomPIN());
        cc.setProfil(new Profil());

        m.addAttribute("customers",customers);
        m.addAttribute("newcustomer", cc);

        return "customers";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute Customer customer, Model model){

        customer.setLastLoginTime(new Date());
        Profil p = customer.getProfil();
        profilRepository.save(p);
        customer.setProfil(null);
        customerRepository.save(customer);
        customer.setProfil(p);
        p.setCustomer(customer);
        customerRepository.saveAndFlush(customer);
        profilRepository.saveAndFlush(p);

        return "redirect:all";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id, Model model){

        Customer c = customerRepository.findOne(id);
        if (c != null){
            customerRepository.delete(c);
        }

        return "redirect:../all";
    }

    @RequestMapping(value = "/show/{id}",  method = RequestMethod.GET)
    public String showCustomer(@PathVariable Long id, Model model){

        Customer customer = customerRepository.findOne(id);
        model.addAttribute("customer",customer);
        float balance = 0;
        try {
            balance = bankAccountRepository.getBalance(id);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        model.addAttribute("balance",balance);

        List<BankAccount> accounts = bankAccountRepository.getBankAccountByCustomerId(id);
        if (accounts == null)
            accounts = new ArrayList<BankAccount>();

        model.addAttribute("accounts",accounts);

        List<Transaction> transactions = transactionRepository.findaAllByCustomerId(id);
        model.addAttribute("transactions",transactions);

        BankAccount newaccount = new BankAccount();
        newaccount.setIsCard(true);
        model.addAttribute("newaccount",newaccount);

        Transaction transaction = new Transaction();
        model.addAttribute("newt",transaction);

        List<String> currencies = new ArrayList<String>();
        for (CurrencyType c : CurrencyType.values()){
            currencies.add(c.toString());
        }

        List<String> tTypes = new ArrayList<String>();
        for (TransactionType t : TransactionType.values()){
            tTypes.add(t.toString());
        }

        List<String> accountsTypes = bankAccountTypesRepository.getAllTypes();

        List<String> accountsNumber = new ArrayList<String>();
        for (BankAccount t : accounts){
            accountsNumber.add(t.getAccountNumber());
        }

        model.addAttribute("currencies",currencies);
        model.addAttribute("ttypes",tTypes);
        model.addAttribute("acctypes",accountsTypes);
        model.addAttribute("accnums",accountsNumber);



        return "show";
    }

    @RequestMapping(value = "/saveaccount/{id}", method = RequestMethod.POST)
    private String createBankAccount(@PathVariable Long id,@ModelAttribute BankAccount bankAccount, Model model){

        Customer c = customerRepository.findOne(id);
        Profil p = profilRepository.findOne(c.getProfil().getId());
        bankAccount.setProfil(p);
        bankAccount.setBalance(0.0f);

        StringBuilder sb = new StringBuilder();
        BankAccount b = null;
        do {
            Random random = new Random();
            int tmp;
            sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                tmp = Math.abs(random.nextInt()) % 10;
                sb.append(tmp);
            }
            b = bankAccountRepository.findOne(sb.toString());
        }while(b != null);

        bankAccount.setAccountNumber(sb.toString());

        bankAccountRepository.saveAndFlush(bankAccount);

        return "redirect:../show/"+id;
    }

    @RequestMapping(value = "/deleteaccount/{id}/{bid}", method = RequestMethod.POST)
    public String deleteAccount(@PathVariable Long id, @PathVariable String bid, Model model){

        BankAccount b = bankAccountRepository.findOne(bid);
        if (b != null){
            bankAccountRepository.delete(b);
        }

        return "redirect:../../show/"+id;
    }

    @RequestMapping(value = "/savetransaction/{id}", method = RequestMethod.POST)
    private String createTransaction(@PathVariable Long id,@ModelAttribute Transaction transaction, Model model){


        BankAccount b = bankAccountRepository.findOne(transaction.getBankAccount().getAccountNumber());

        if (b != null){
            transaction.setBankAccount(b);
            transaction.setTransactionDateTime(new Date());
            transactionRepository.saveAndFlush(transaction);
        }

        if(transaction.getTransactionType().equals(TransactionType.INCOMING))
            b.setBalance(b.getBalance()+transaction.getAmount());
        else
            b.setBalance(b.getBalance()-transaction.getAmount());
        bankAccountRepository.saveAndFlush(b);

        return "redirect:../show/"+id;
    }

    @RequestMapping(value = "/deletetransaction/{id}/{tid}", method = RequestMethod.POST)
    public String deleteTransaction(@PathVariable Long id, @PathVariable Long tid, Model model){

        Logger log = Logger.getLogger("Contorller");
        log.info("Delete transaction: "+tid+ ", on customer id: "+id);

        Transaction t = transactionRepository.findOne(tid);
        if (t != null){
            BankAccount b = bankAccountRepository.findOne(t.getBankAccount().getAccountNumber());
            if(t.getTransactionType().equals(TransactionType.INCOMING))
                b.setBalance(b.getBalance()-t.getAmount());
            else
                b.setBalance(b.getBalance() + t.getAmount());
            bankAccountRepository.saveAndFlush(b);
            transactionRepository.delete(t);
        }

        return "redirect:../../show/"+id;
    }

    private String randomPIN(){
        Random random = new Random();
        long  r = random.nextLong();
        r = Math.abs(r);
        String result = Long.toString(r);
        if (result.length()>6){
            result = result.substring(0,6);
        }else if (result.length()<6){
            int tofill = 6 - result.length();
            for (int i = 0; i < tofill ;i++){
                result = result.concat(""+random.nextInt(9));
            }
        }

        return result;
    }


}
