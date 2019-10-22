import service.SportsBettingService;
import view.View;
import domain.builders.WagerBuilder;
import domain.OutcomeOdd;
import domain.SportEvent;
import domain.Wager;

import java.math.BigDecimal;
import java.util.List;

public class App {
    private SportsBettingService service;
    private View view;

    public App(SportsBettingService service, View view) {
        this.service = service;
        this.view = view;
    }

    public static void main(String[] args){
        App app = new App(new SportsBettingService(), new View());
        app.play();
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
            if (oc == null){
                System.out.println("Not valid outcome!");
                break;
            }
            BigDecimal amount = view.readWagerAmount();
            if (service.getBuilder().getPlayer().getBalance().compareTo(amount) >= 0){
                Wager wager = new WagerBuilder()
                        .setAmout(amount)
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

    private void play(){
        createPlayer();
        doBetting();
        calculateResults();
        printResults();
    }
}
