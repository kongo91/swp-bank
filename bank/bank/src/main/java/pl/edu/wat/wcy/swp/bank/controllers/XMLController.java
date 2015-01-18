package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.swp.bank.dtos.BankAccountDTO;
import pl.edu.wat.wcy.swp.bank.dtos.TransactionDTO;
import pl.edu.wat.wcy.swp.bank.dtos.convertrs.BankAccountConverter;
import pl.edu.wat.wcy.swp.bank.dtos.convertrs.TransactionConverter;
import pl.edu.wat.wcy.swp.bank.entities.*;
import pl.edu.wat.wcy.swp.bank.repositories.BankAccountRepository;
import pl.edu.wat.wcy.swp.bank.repositories.CustomerRepository;
import pl.edu.wat.wcy.swp.bank.repositories.ProfilRepository;
import pl.edu.wat.wcy.swp.bank.repositories.TransactionRepository;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Konrad on 2015-01-10.
 */
@Controller
@RequestMapping("/xml")
public class XMLController {

    private final static String subdialog_login_line = "<!--SAYDATELOGIN-->";
    private final static String subdialog_is_login = "<!--ISLOGIN-->";
    private final static String subdialog_balance_line = "<!--SAYBALANCE-->";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProfilRepository profilRepository;


    @RequestMapping(value = "/login/{id}/{pin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getLoggin(@PathVariable Long id, @PathVariable String pin){

        Logger log = Logger.getLogger("log");
        log.info("Invoked getLoggin with param id: "+id+" and pin: "+pin);
        Customer customer = customerRepository.findByIdAndPin(id,pin);

        File xml = new File("D:\\Workspace\\xml\\loggin_subdialog.xml");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {

            br = new BufferedReader(new FileReader(xml));

            String line;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String lastLogin = null;
            if (customer != null){
                lastLogin = sdf.format(customer.getLastLoginTime());
            }
            while ((line = br.readLine())!= null){
                if (line.contains(subdialog_login_line)){
                    if (customer != null)
                        line = "Hi "+customer.getProfil().getSurname()+" "+customer.getProfil().getName()+"! You visited as recently on <say-as interpret-as=\"vxml:date\">"+lastLogin+"</say-as>";
                    else
                        line = "Sorry! Bad Customer ID or PIN!";
                }else if (line.contains(subdialog_is_login)){
                        int isLogin = customer != null ? 1 : 0;
                        line = "<var name=\"succeed\" expr=\"'"+isLogin+"'\" />";
                }
                sb.append(line).append("\n");
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (customer!= null) customer.setLastLoginTime(new Date());

        return sb.toString();
    }

    @RequestMapping(value = "/balance/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getBalance(@PathVariable Long id){

        Logger log = Logger.getLogger("log");
        log.info("Invoked getLoggin with param id: "+id);

        float balance = bankAccountRepository.getBalance(id);

        File xml = new File("D:\\Workspace\\xml\\balance_subdialog.xml");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {

            br = new BufferedReader(new FileReader(xml));

            String line;


            while ((line = br.readLine())!= null){
                if (line.contains(subdialog_balance_line)){
                    line = "Your currently balance account is <say-as interpret-as=\"vxml:currency\">USD"+balance+"</say-as>";
                }
                sb.append(line).append("\n");
            }

            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }

    @RequestMapping(value = "/transaction/incoming/{cid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TransactionDTO getLastIncomingTransaction(@PathVariable Long cid){

        Transaction t;
        try {
            t = transactionRepository.findLastTransactionsByType(cid, TransactionType.INCOMING).get(0);
        }catch(IndexOutOfBoundsException e){
            t = new Transaction();
            t.setTransactionType(TransactionType.INCOMING);
        }
        return TransactionConverter.toDTO(t);
    }

    @RequestMapping(value = "/transaction/outbound/{cid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TransactionDTO getLastOutboundTransaction(@PathVariable Long cid){

        Transaction t;
        try {
            t = transactionRepository.findLastTransactionsByType(cid, TransactionType.OUTBOUND).get(0);
        }catch(IndexOutOfBoundsException e){
            t = new Transaction();
            t.setTransactionType(TransactionType.OUTBOUND);
        }
        return TransactionConverter.toDTO(t);
    }

    @RequestMapping(value = "/saveaccount/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    private BankAccountDTO createBankAccount(@PathVariable Long id,
                                     @RequestParam(value = "isCard", required = false, defaultValue = "true") Boolean isCard,
                                     @RequestParam(value = "type", required = false, defaultValue = "NORMAL") String type,
                                     @RequestParam(value = "curr", required = false, defaultValue = "USD") String currency){

        Logger logger = Logger.getLogger("XMLController");
        logger.info("CID: "+id+", isCard: "+isCard+", type: "+type+", currency: "+currency);


        Customer c = customerRepository.findOne(id);
        Profil p = profilRepository.findOne(c.getProfil().getId());
        BankAccount bankAccount = new BankAccount();
        bankAccount.setProfil(p);
        bankAccount.setBalance(0.0f);
        bankAccount.setIsCard(isCard);

        for(BankAccountType t: BankAccountType.values()){
            if (type.toLowerCase().equals(t.name().toLowerCase())){
                bankAccount.setAccountType(t);
                break;
            }
        }

        for(CurrencyType ct: CurrencyType.values()){
            if (currency.toLowerCase().equals(ct.name().toLowerCase())){
                bankAccount.setCurrency(ct);
                break;
            }
        }

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

        BankAccountDTO bankAccountDTO = BankAccountConverter.toDTO(bankAccount);

        return bankAccountDTO;
    }


}
