package Application;

import Model.Builders.PlayerBuilder;
import Model.Currency;
import Model.Player;
import Model.SportEvent;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class View {

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

        Scanner in = new Scanner(System.in);
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
        for(SportEvent se : events){
            System.out.println(se);
        }
    }
}
