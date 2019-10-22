package view;

import domain.*;
import domain.builders.PlayerBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class View implements IView {

    private Scanner in;

    public View() {
        in = new Scanner(System.in);
    }

    public Player readPlayerData(){
        PlayerBuilder builder = new PlayerBuilder();
        System.out.println("What is your name?");

        String name = in.nextLine();
        /*if (name.trim().equals("")){
            System.out.println("Not valid name!");
            readPlayerData();
        }*/
        builder.setName(name);

        System.out.println("How much money do you have (more than 0)?");

        String balance = in.nextLine();

        /*if (balance.trim().equals("") || new BigDecimal(balance).compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Not valid amount!\n");
            readPlayerData();
        }*/

        builder.setBalance(new BigDecimal(balance));

        System.out.println("What is your currency? (HUF, EUR or USD)");
        builder.setCurrency(Currency.valueOf(in.nextLine().toUpperCase()));

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

    public BigDecimal readWagerAmount(){
        System.out.println("What amount do you wish to bet on it?");

        //megjegyzi az értéket akkor is ha rossz javítani!!!!
        BigDecimal amount = new BigDecimal(in.nextLine());

        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println("Not valid betting amount!\n");
            readWagerAmount();
        }

        return amount;
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
