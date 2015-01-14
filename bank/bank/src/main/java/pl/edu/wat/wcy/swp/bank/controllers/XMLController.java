package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.wcy.swp.bank.dtos.TransactionDTO;
import pl.edu.wat.wcy.swp.bank.dtos.convertrs.TransactionConverter;
import pl.edu.wat.wcy.swp.bank.entities.Customer;
import pl.edu.wat.wcy.swp.bank.entities.Transaction;
import pl.edu.wat.wcy.swp.bank.entities.TransactionType;
import pl.edu.wat.wcy.swp.bank.repositories.BankAccountRepository;
import pl.edu.wat.wcy.swp.bank.repositories.CustomerRepository;
import pl.edu.wat.wcy.swp.bank.repositories.TransactionRepository;

import java.io.*;
import java.text.SimpleDateFormat;
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


    @RequestMapping(value = "/login/{id}/{pin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getLoggin(@PathVariable Long id, @PathVariable Integer pin){

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
                        line = "Hi! You visited as recently on <say-as interpret-as=\"vxml:date\">"+lastLogin+"</say-as>";
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
            t = transactionRepository.findLastTransaction(cid, TransactionType.INCOMING).get(0);
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
            t = transactionRepository.findLastTransaction(cid, TransactionType.OUTBOUND).get(0);
        }catch(IndexOutOfBoundsException e){
            t = new Transaction();
            t.setTransactionType(TransactionType.OUTBOUND);
        }
        return TransactionConverter.toDTO(t);
    }


}
