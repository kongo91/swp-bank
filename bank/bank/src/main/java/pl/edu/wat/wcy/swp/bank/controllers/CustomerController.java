package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.edu.wat.wcy.swp.bank.entities.Customer;
import pl.edu.wat.wcy.swp.bank.services.interfaces.CustomerService;

import java.util.List;

/**
 * Created by Konrad on 2015-01-03.
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/hello")
    public ModelAndView hello(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        String str = "Text from Controller";
        mav.addObject("str",str);

        List<Customer> customers =  customerService.findAll();
        StringBuilder sb = new StringBuilder();
        for (Customer c : customers){
            sb.append(c.getCustomerId()).append("_");
        }

        mav.addObject("customers",sb.toString());

        return mav;
    }


}
