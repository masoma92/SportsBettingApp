package com.example.sportsbetting.app.view;

import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.domain.builders.PlayerBuilder;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class View implements IView {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private Scanner in;
    @Autowired
    private PlayerBuilder builder;
    @Autowired
    private MessageSource messageSource;
    private Locale locale;

    public View(Locale locale) {
        this.locale = locale;
    }


    public Player readPlayerData(){
        try {
            logger.info(messageSource.getMessage("question_name", null, "defaultN", locale));

            String name = in.nextLine();
            if (name.trim().equals("")) {
                throw new Exception(messageSource.getMessage("notvalid_name", null, "defaultNVN", locale));
            }
            else{
                builder.setName(name);
            }

            logger.info(messageSource.getMessage("question_money", null, "defaultQM", locale));

            String balance = in.nextLine();

            if (balance.trim().equals("") || new BigDecimal(balance).compareTo(BigDecimal.ZERO) <= 0){
                throw new Exception(messageSource.getMessage("notvalid_amount", null, "defaultNVA", locale));
            }
            else{
                builder.setBalance(new BigDecimal(balance));
            }

            logger.info(messageSource.getMessage("question_currency", null, "defaultQC", locale));
            builder.setCurrency(Currency.valueOf(in.nextLine().toUpperCase()));

            return builder.getPlayer();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return readPlayerData();
        }
    }

    public void printWelcomeMessage(Player player){
        logger.info(messageSource.getMessage("greetings_name", new Object[] {builder.getName()}, "defwelcome", locale));
    }

    public void printBalance(Player player){
        logger.info(messageSource.getMessage("balance", new Object[] {player.getBalance().toString(), player.getCurrency()}, "defB", locale));
    }

    public void printOutcomeOdds(List<SportEvent> events){
        logger.info(messageSource.getMessage("question_eventnum", null, "defQE", locale));
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
        logger.info(messageSource.getMessage("notvalid_outcome", null, "defNO", locale));
        return selectOutcomeOdd(events);
    }

    public BigDecimal readWagerAmount(){
        logger.info(messageSource.getMessage("question_betamount", null, "defQBA", locale));

        BigDecimal amount = new BigDecimal(in.nextLine());

        if (amount.compareTo(BigDecimal.ZERO) <= 0){
            logger.info(messageSource.getMessage("notvalid_bettingamout",null, "defNVBA", locale));
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
        logger.info(messageSource.getMessage("wager", new Object[]{
                wager.getOdd().getOutcome().getBet().getType(),
                wager.getOdd().getOutcome().getDescription(),
                wager.getOdd().getOutcome().getBet().getEvent().getTitle(),
                wager.getOdd().getValue(),
                wager.getAmount(),
                ending
        }, "defW", locale));
    }

    public void printNotEnoughBalance(Player player){
        logger.info(messageSource.getMessage("no_money", new Object[] {player.getBalance().toString(), player.getCurrency()}, "defNM", locale));
    }

    public void printResults(Player player, List<Wager> wagers){
        logger.info(messageSource.getMessage("results", null, "defR", locale));
        for(Wager w : wagers){
            printWagerSaved(w);
        }
        printBalance(player);
    }
}
