package pl.edu.wat.wcy.swp.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Konrad on 2015-01-03.
 */
@Controller
public class CustomerController {

    @RequestMapping("/hello")
    public ModelAndView hello(){

        ModelAndView mav = new ModelAndView();
        mav.setViewName("hello");

        String str = "Text from Controller";
        mav.addObject("str",str);

        return mav;
    }


}
