package Application;

import Model.Builders.PlayerBuilder;

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
        view.printOutcomeOdds(this.service.findAllSportEvents());
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
