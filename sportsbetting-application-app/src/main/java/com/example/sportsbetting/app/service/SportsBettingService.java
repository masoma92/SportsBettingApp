package com.example.sportsbetting.app.service;

import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.app.data.DataBuilder;
import com.example.sportsbetting.repository.PlayerRepository;
import com.example.sportsbetting.repository.ResultRepository;
import com.example.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

        for (Wager wager : wagers){
            BigDecimal newBalance = wager.getPlayer().getBalance().subtract(wager.getAmount());
            this.builder.getPlayer().setBalance(newBalance);
            playerRepository.save(this.builder.getPlayer());
        }

        List<Outcome> winnerOutcomes = new ArrayList<>();

        //chooses winning outcomes
        chooseWinningOutcomes(winnerOutcomes);

        //updating wagers iswin, isprocessed and player balance depending on the wagers
        updateWagerAndPlayer(winnerOutcomes, wagers);
    }

    private void chooseWinningOutcomes(List<Outcome> winnerOutcomes){
        for (SportEvent se : this.builder.getEvents()){
            if (LocalDate.parse(se.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")).isBefore(LocalDate.now())){
                List<Outcome> winnerOutcomesForEvent = new ArrayList<>();
                for (Bet b : se.getBets()){
                    for (Outcome o : b.getOutcomes()){
                        if(new Random().nextBoolean()){
                            winnerOutcomes.add(o);
                            winnerOutcomesForEvent.add(o);
                            break;
                        }
                    }
                }
                Result r = new Result(winnerOutcomesForEvent);
                se.setResult(r);
                resultRepository.save(r);
                winnerOutcomesForEvent.clear();
            }
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
