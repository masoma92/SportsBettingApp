package Application;

import Model.*;
import Model.Builders.PlayerBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class View {

    private Scanner in;

    public View() {
        in = new Scanner(System.in);
    }

    void currencyChooser(PlayerBuilder builder, Scanner in){
        System.out.println("What is your currency? (HUF, EUR or USD)");
        String s = in.nextLine();
        switch(s){
            case "EUR":
                builder.setCurrency(Currency.EUR);
                break;
            case "HUF":
                builder.setCurrency(Currency.HUF);
                break;
            case "USD":
                builder.setCurrency(Currency.USD);
                break;
            default:
                System.out.println();
                currencyChooser(builder, in);
        }
    }

    public Player readPlayerData(){
        PlayerBuilder builder = new PlayerBuilder();
        System.out.println("What is your name?");

        builder.setName(in.nextLine());

        System.out.println("How much money do you have (more than 0)?");
        builder.setBalance(new BigDecimal(in.nextLine()));

        currencyChooser(builder, in);
        return builder.getPlayer();
    }

    public void printWelcomeMessage(Player player){
        System.out.println(String.format("Hello " + player.getName() + "!"));
    }

    public void printBalance(Player player){
        System.out.println(String.format("Your balance is " + player.getBalance() + " " + player.getCurrency()));
    }

    public void printOutcomeOdds(List<SportEvent> events){
        System.out.println("What are you want to bet on? (choose a number or press q for quit)");
        for(SportEvent se : events){
            System.out.println(se);
        }
    }

    public OutcomeOdd selectOutcomeOdd(List<SportEvent> events){
        String input = in.nextLine();
        if (input.equals("q")){
            return null;
        }
        else {
            int inputnum = Integer.parseInt(input);
            int i = 0;

            for (SportEvent e : events){
                for (Bet b : e.getBets()){
                    for (Outcome o : b.getOutcomes()){
                        for (OutcomeOdd od : o.getOutcomeOdds()){
                            if (++i == inputnum){
                                return od;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public BigDecimal readWagerAmout(){
        System.out.println("What amount do you wish to bet on it?");
        return new BigDecimal(in.nextLine());
    }

    public void printWagerSaved(Wager wager){
        String ending = wager.isProcessed() ? "" : " saved";
        if (ending.equals("")){
            ending = wager.isWin() ? "win: true" : "win: false";
        }
        System.out.println("Wager: " + wager.getOdd().getOutcome().getBet().getType() + " = " + wager.getOdd().getOutcome().getDescription()
                + ", Event: " + wager.getOdd().getOutcome().getBet().getEvent().getTitle() + " [odd: " + wager.getOdd().getValue() + ", amount: " +
                wager.getAmount()+"]" + ending);
    }

    public void printNotEnoughBalance(Player player){
        System.out.println("You don't have enough money, your balance is " + player.getBalance() + " " + player.getCurrency());
    }

    public void printResults(Player player, List<Wager> wagers){
        System.out.println("Results: ");
        for(Wager w : wagers){
            printWagerSaved(w);
        }
        printBalance(player);
    }
}
