package com.example.sportsbetting.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postLoginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        return model;
    }


}
