package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.edu.wat.wcy.swp.bank.entities.Customer;
import pl.edu.wat.wcy.swp.bank.repositories.BankAccountRepository;
import pl.edu.wat.wcy.swp.bank.repositories.CustomerRepository;

import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

/**
 * Created by Konrad on 2015-01-10.
 */
@Controller
@RequestMapping("/xml")
public class XMLController {

    private final static String subdialog_login_line = "<!--SAYDATELOGIN-->";
    private final static String subdialog_balance_line = "<!--SAYBALANCE-->";

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;


    @RequestMapping(value = "/login/{id}/{pin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
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
            String lastLogin = sdf.format(customer.getLastLoginTime());
            while ((line = br.readLine())!= null){
                if (line.contains(subdialog_login_line)){
                    line = "Hi! You visited as recently on <say-as interpret-as=\"vxml:date\">"+lastLogin+"</say-as>";
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
    @ResponseBody
    public String getBalance(@PathVariable Long id){

        Logger log = Logger.getLogger("log");
        log.info("Invoked getLoggin with param id: "+id);

        float balance = bankAccountRepository.getBalance(id);

        File xml = new File("D:\\Workspace\\xml\\loggin_subdialog.xml");
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {

            br = new BufferedReader(new FileReader(xml));

            String line;


            while ((line = br.readLine())!= null){
                if (line.contains(subdialog_login_line)){
                    line = "Your balance account is <say-as interpret-as=\"vxml:date\">USD"+balance+"</say-as>";
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


}
