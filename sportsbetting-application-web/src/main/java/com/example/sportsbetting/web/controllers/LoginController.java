package com.example.sportsbetting.web.controllers;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private PlayerRepository playerRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView postLoginPage(String input_email, String input_password) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        Player player = playerRepository.findById(1).get();
        if (player.getEmail().equals(input_email) && player.getPassword().equals(input_password)){
            model.setViewName("redirect:/home");
        }
        return model;
    }


}
