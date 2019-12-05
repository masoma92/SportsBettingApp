package com.example.sportsbetting.web.controllers;

import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class LoginController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView getDefaultPage(Locale locale) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        localizeLogin(model, locale);

        locale = LocaleContextHolder.getLocale();
        model.addObject("locale", locale);
        return model;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.POST)
    public ModelAndView postDefaultPage(Locale locale, String input_email, String input_password) {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");

        Player player = playerRepository.findById(1).get();
        // in business life never do this!!!!
        if (player.getEmail().equals(input_email) && player.getPassword().equals(input_password)){
            model.setViewName("redirect:/home");
        }
        return model;
    }

    private ModelAndView localizeLogin(ModelAndView model, Locale locale) {
        model.addObject("sign_in", messageSource.getMessage("sign_in", null, locale));
        model.addObject("email_address", messageSource.getMessage("email_address", null, locale));
        model.addObject("password", messageSource.getMessage("password", null, locale));
        return model;
    }

}
