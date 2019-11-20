package com.example.sportsbetting.app.service;

import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.domain.DataBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportsBettingService implements ISportBettingService {

    @Autowired
    private DataBuilder builder;

    public SportsBettingService() {
    }

    public DataBuilder getBuilder() {
        return builder;
    }

    public void savePlayer(Player player){
        this.builder.setPlayer(player);
    }

    public Player findPlayer(){
        return builder.getPlayer();
    }

    public List<SportEvent> findAllSportEvents() {
        return this.builder.getEvents();
    }

    public void saveWager(Wager wager){
        BigDecimal newBalance = wager.getPlayer().getBalance().subtract(wager.getAmount());
        this.builder.getPlayer().setBalance(newBalance);
        wager.setProcessed(true);
        this.builder.addWager(wager);
    }

    public List<Wager> findAllWagers(){
        return this.builder.getWagers();
    }

    public void calculateResults(){
        List<Wager> wagers = this.builder.getWagers();

        List<Outcome> winnerOutcomes = new ArrayList<>();

        //kiválasztja a nyerő kimeneteleket
        chooseWinningOutcomes(winnerOutcomes);

        //kiválasztja a nyerő oddsokat a kimeneteleken belül
        for (Outcome o : winnerOutcomes){
            for (Wager w : wagers){
                if (o.getOutcomeOdds().contains(w.getOdd())){
                    w.setWin(true);
                    BigDecimal newBalance = w.getPlayer().getBalance().add(w.getOdd().getValue().multiply(w.getAmount()));
                    w.getPlayer().setBalance(newBalance);
                }
            }
        }
    }

    void chooseWinningOutcomes(List<Outcome> winnerOutcomes){
        for (SportEvent se : this.builder.getEvents()){
            for (Bet b : se.getBets()){
                for (Outcome o : b.getOutcomes()){
                    if(new Random().nextBoolean()){
                        winnerOutcomes.add(o);
                        break;
                    }
                }
            }
            se.setResult(new Result(winnerOutcomes));
        }
    }
}
