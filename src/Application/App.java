package Application;

import Model.Builders.PlayerBuilder;
import Model.Outcome;
import Model.OutcomeOdd;
import Model.SportEvent;

import java.math.BigDecimal;
import java.util.List;

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
        view.printOutcomeOdds(events);
        OutcomeOdd oc = view.selectOutcomeOdd(events);
        BigDecimal amount = view.readWagerAmout();

    }

    private void calculateResults(){

    }

    private void printResults(){

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
    }


    public static void main(String[] args){
        App app = new App(new SportsBettingService(), new View());
        app.play();
    }
}
