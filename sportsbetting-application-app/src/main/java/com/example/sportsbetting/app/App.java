package com.example.sportsbetting.app;

import com.example.sportsbetting.app.service.SportsBettingService;
import com.example.sportsbetting.app.view.View;
import com.example.sportsbetting.domain.OutcomeOdd;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.Wager;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class App {

    @Autowired
    private SportsBettingService service;
    @Autowired
    private View view;

    public App() {
    }

    public void play(){
        createPlayer();
        doBetting();
        calculateResults();
        printResults();
    }

    private void createPlayer(){
        service.savePlayer(view.readPlayerData());
        view.printWelcomeMessage(service.getBuilder().getPlayer());
        view.printBalance(service.getBuilder().getPlayer());
    }

    private void doBetting(){
        List<SportEvent> events = service.findAllSportEvents();
        while (service.getBuilder().getPlayer().getBalance().compareTo(BigDecimal.ZERO) > 0){
            view.printOutcomeOdds(events);
            OutcomeOdd oc = view.selectOutcomeOdd(events);
            BigDecimal amount = view.readWagerAmount();
            if (service.getBuilder().getPlayer().getBalance().compareTo(amount) >= 0){
                Wager wager = new Wager.WagerBuilder()
                        .setAmount(amount)
                        .setOutcomeOdd(oc)
                        .setPlayer(service.getBuilder().getPlayer())
                        .setCurrency(service.getBuilder().getPlayer().getCurrency())
                        .getWager();
                view.printWagerSaved(wager);
                service.saveWager(wager);
                view.printBalance(service.getBuilder().getPlayer());
            }
            else{
                view.printNotEnoughBalance(service.getBuilder().getPlayer());
            }
        }
    }

    private void calculateResults(){
        service.calculateResults();
    }

    private void printResults(){
        view.printResults(this.service.getBuilder().getPlayer(), this.service.getBuilder().getWagers());
    }


}
