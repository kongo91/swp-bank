package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wat.wcy.swp.bank.entities.Customer;
import pl.edu.wat.wcy.swp.bank.repositories.CustomerRepository;
import pl.edu.wat.wcy.swp.bank.services.interfaces.CustomerService;

import java.util.Date;
import java.util.List;

/**
 * Created by Konrad on 2015-01-03.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/hello")
    public ModelAndView hello(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        String str = "Text from Controller";
        mav.addObject("str",str);

        Customer customer = new Customer();
        customer.setCustomerId(100+repository.count());
        customer.setPIN(1233);
        customer.setLastLoginTime(new Date());
        repository.saveAndFlush(customer);

        List<Customer> customers =  customerService.findAll();
        StringBuilder sb = new StringBuilder();
        for (Customer c : customers){
            sb.append(c.getCustomerId()).append("_");
        }

        mav.addObject("customers",sb.toString());

        return mav;
    }


}
