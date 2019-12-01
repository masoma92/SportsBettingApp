package com.example.sportsbetting.web.controllers;

import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.app.service.SportsBettingService;
import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    private SportsBettingService service;

    @Autowired
    private DataBuilder builder;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("player", builder.getPlayer());
        model.addObject("wagers", builder.getWagers());
        return model;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView setPlayer(String playername, String dateofbirth, String accountnumber, String currency, BigDecimal balance) {
        ModelAndView model = new ModelAndView();
        Player player = builder.getPlayer();
        player.setName(playername);
        player.setBirth(LocalDate.of(Integer.parseInt(dateofbirth.split("-")[0]),Integer.parseInt(dateofbirth.split("-")[1]), Integer.parseInt(dateofbirth.split("-")[1])));
        player.setAccountNumber(accountnumber);
        player.setCurrency(Currency.valueOf(currency));
        player.setBalance(balance);
        service.savePlayer(player);
        service.calculateResults();

        model.setViewName("home");
        model.addObject("player", player);
        model.addObject("wagers", builder.getWagers());

        return model;
    }
}
