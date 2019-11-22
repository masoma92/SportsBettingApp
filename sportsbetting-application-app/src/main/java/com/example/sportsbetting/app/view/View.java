package com.example.sportsbetting.app.view;

import com.example.sportsbetting.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class View implements IView {

    private static int accountNumber = 0;

    private Scanner in;
    private Locale locale;

    @Autowired
    private Player.PlayerBuilder builder;
    @Autowired
    private MessageSource messageSource;

    public View(Locale locale) {
        this.locale = locale;
        in = new Scanner(System.in);
    }

    public Player readPlayerData(){
        try {
            System.out.println(messageSource.getMessage("question_name", null, "defaultN", locale));

            String name = in.nextLine();
            if (name.trim().equals("")) {
                throw new Exception(messageSource.getMessage("notvalid_name", null, "defaultNVN", locale));
            }
            else{
                builder.setName(name);
            }

            System.out.println(messageSource.getMessage("question_money", null, "defaultQM", locale));

            String balance = in.nextLine();

            if (balance.trim().equals("") || new BigDecimal(balance).compareTo(BigDecimal.ZERO) <= 0){
                throw new Exception(messageSource.getMessage("notvalid_amount", null, "defaultNVA", locale));
            }
            else{
                builder.setBalance(new BigDecimal(balance));
            }

            System.out.println(messageSource.getMessage("question_currency", null, "defaultQC", locale));
            builder.setCurrency(Currency.valueOf(in.nextLine().toUpperCase()));

            builder.setAccountNumber(++accountNumber);
            builder.setBirthDate(LocalDate.of(1912, 12, 12));

            return builder.getPlayer();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return readPlayerData();
        }
    }

    public void printWelcomeMessage(Player player){
        System.out.println(messageSource.getMessage("greetings_name", new Object[] {builder.getName()}, "defwelcome", locale));
    }

    public void printBalance(Player player){
        System.out.println(messageSource.getMessage("balance", new Object[] {player.getBalance().toString(), player.getCurrency()}, "defB", locale));
        System.out.println();
    }

    public void printOutcomeOdds(List<SportEvent> events){
        System.out.println(messageSource.getMessage("question_eventnum", null, "defQE", locale));
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
        System.out.println(messageSource.getMessage("notvalid_outcome", null, "defNO", locale));
        return selectOutcomeOdd(events);
    }

    public BigDecimal readWagerAmount(){
        System.out.println(messageSource.getMessage("question_betamount", null, "defQBA", locale));

        BigDecimal amount = new BigDecimal(in.nextLine());

        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            System.out.println(messageSource.getMessage("notvalid_bettingamout",null, "defNVBA", locale));
            return readWagerAmount();
        }
        else{
            return amount;
        }
    }

    public void printWagerSaved(Wager wager){
        String ending = wager.isProcessed() ? "" : messageSource.getMessage("wager_issaved", null, "defWIS", locale);
        if (ending.equals("")){
            ending = wager.isWin() ? messageSource.getMessage("wager_win", null, "defWW", locale) :
                    messageSource.getMessage("wager_lose", null, "defWL", locale);
        }
        System.out.println(messageSource.getMessage("wager", new Object[]{
                wager.getOdd().getOutcome().getBet().getType(),
                wager.getOdd().getOutcome().getDescription(),
                wager.getOdd().getOutcome().getBet().getEvent().getTitle(),
                wager.getOdd().getValue(),
                wager.getAmount().toString(),
                ending
        }, "defW", locale));
    }

    public void printNotEnoughBalance(Player player){
        System.out.println(messageSource.getMessage("no_money", new Object[] {player.getBalance().toString(), player.getCurrency()}, "defNM", locale));
    }

    public void printResults(Player player, List<Wager> wagers){
        System.out.println(messageSource.getMessage("results", null, "defR", locale));
        for(Wager w : wagers){
            printWagerSaved(w);
        }
        printBalance(player);
    }
}
