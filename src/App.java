import service.SportsBettingService;
import view.View;
import domain.builders.WagerBuilder;
import domain.OutcomeOdd;
import domain.SportEvent;
import domain.Wager;

import java.math.BigDecimal;
import java.util.List;

public class App {

    private void createPlayer(){
        service.savePlayer(this.view.readPlayerData());
        this.view.printWelcomeMessage(service.getBuilder().getPlayer());
        this.view.printBalance(service.getBuilder().getPlayer());
    }

    private void doBetting(){
        List<SportEvent> events = this.service.findAllSportEvents();
        while (this.service.getBuilder().getPlayer().getBalance().compareTo(BigDecimal.ZERO) > 0){
            view.printOutcomeOdds(events);
            OutcomeOdd oc = view.selectOutcomeOdd(events);
            if (oc == null) break;
            BigDecimal amount = view.readWagerAmout();
            if (this.service.getBuilder().getPlayer().getBalance().compareTo(amount) >= 0){
                Wager wager = new WagerBuilder()
                        .setAmout(amount)
                        .setOutcomeOdd(oc)
                        .setPlayer(this.service.getBuilder().getPlayer())
                        .setCurrency(this.service.getBuilder().getPlayer().getCurrency())
                        .getWager();
                this.view.printWagerSaved(wager);
                this.service.saveWager(wager);
                this.view.printBalance(this.service.getBuilder().getPlayer());
            }
            else{
                this.view.printNotEnoughBalance(this.service.getBuilder().getPlayer());
            }
        }
    }

    private void calculateResults(){
        this.service.calculateResults();
    }

    private void printResults(){
        this.view.printResults(this.service.getBuilder().getPlayer(), this.service.getBuilder().getWagers());
    }

    private SportsBettingService service;
    private View view;

    public App(SportsBettingService service, View view) {
        this.service = service;
        this.view = view;
    }

    private void play(){
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
