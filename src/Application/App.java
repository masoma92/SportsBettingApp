package Application;

import Model.Builders.PlayerBuilder;
import Model.Builders.WagerBuilder;
import Model.Outcome;
import Model.OutcomeOdd;
import Model.SportEvent;
import Model.Wager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.jar.Attributes;

public class App {

    static public int accountNumber = 0;
    static public int getAccountNumber(){
        return App.accountNumber++;
    }

    private void createPlayer(){
        service.savePlayer(this.view.readPlayerData());
        this.view.printWelcomeMessage(service.getBuilder().getPlayer());
        this.view.printBalance(service.getBuilder().getPlayer());
    }

    private void doBetting(){
        List<SportEvent> events = this.service.findAllSportEvents();
        while (this.service.builder.getPlayer().getBalance().compareTo(BigDecimal.ZERO) > 0){
            view.printOutcomeOdds(events);
            OutcomeOdd oc = view.selectOutcomeOdd(events);
            if (oc == null) break;
            BigDecimal amount = view.readWagerAmout();
            if (this.service.builder.getPlayer().getBalance().compareTo(amount) >= 0){
                Wager wager = new WagerBuilder()
                        .setAmout(amount)
                        .setOutcomeOdd(oc)
                        .setPlayer(this.service.builder.getPlayer())
                        .setCurrency(this.service.builder.getPlayer().getCurrency())
                        .getWager();
                this.view.printWagerSaved(wager);
                this.service.saveWager(wager);
                this.view.printBalance(this.service.builder.getPlayer());
            }
            else{
                this.view.printNotEnoughBalance(this.service.builder.getPlayer());
            }
        }
    }

    private void calculateResults(){
        this.service.calculateResults();
    }

    private void printResults(){
        this.view.printResults(this.service.builder.getPlayer(), this.service.builder.getWagers());
    }

    SportsBettingService service;
    View view;

    public App(SportsBettingService service, View view) {
        this.service = service;
        this.view = view;
    }

    public void play(){
        createPlayer();
        doBetting();
        calculateResults();
        printResults();
    }


    public static void main(String[] args){
        App app = new App(new SportsBettingService(), new View());
        app.play();
    }
}
