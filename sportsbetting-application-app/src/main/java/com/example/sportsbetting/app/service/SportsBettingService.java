package com.example.sportsbetting.app.service;

import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.repository.PlayerRepository;
import com.example.sportsbetting.repository.ResultRepository;
import com.example.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportsBettingService implements ISportBettingService {

    @Autowired
    private DataBuilder builder;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private WagerRepository wagerRepository;

    @Autowired
    private ResultRepository resultRepository;

    public SportsBettingService() {
    }

    public DataBuilder getBuilder() {
        return builder;
    }

    public void savePlayer(Player player){
        this.builder.setPlayer(player);
        playerRepository.save(this.builder.getPlayer());
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
        this.builder.addWager(wager);
        playerRepository.save(this.builder.getPlayer());
        wagerRepository.save(wager);
    }

    public List<Wager> findAllWagers(){
        return this.builder.getWagers();
    }

    public void calculateResults(){
        List<Wager> wagers = this.builder.getWagers();

        List<Outcome> winnerOutcomes = new ArrayList<>();

        //chooses winning outcomes
        chooseWinningOutcomes(winnerOutcomes);

        //updating wagers iswin, isprocessed and player balance depending on the wagers
        updateWagerAndPlayer(winnerOutcomes, wagers);
    }

    private void chooseWinningOutcomes(List<Outcome> winnerOutcomes){
        for (SportEvent se : this.builder.getEvents()){
            for (Bet b : se.getBets()){
                for (Outcome o : b.getOutcomes()){
                    if(new Random().nextBoolean()){
                        winnerOutcomes.add(o);
                        break;
                    }
                }
            }
            Result r = new Result(winnerOutcomes);
            se.setResult(r);
            resultRepository.save(r);
        }
    }

    private void updateWagerAndPlayer(List<Outcome> winnerOutcomes, List<Wager> wagers){
        for (Outcome o : winnerOutcomes){
            for (Wager w : wagers){
                if (o.getOutcomeOdds().contains(w.getOdd())){
                    w.setWin(true);
                    wagerRepository.save(w);
                    BigDecimal newBalance = w.getPlayer().getBalance().add(w.getOdd().getValue().multiply(w.getAmount()));
                    w.getPlayer().setBalance(newBalance);
                    w.setProcessed(true);
                    wagerRepository.save(w);
                    playerRepository.save(w.getPlayer());
                }
            }
        }
    }
}
