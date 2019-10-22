package service;

import domain.*;
import domain.builders.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SportsBettingService implements ISportBettingService {

    private DataBuilder builder;

    public SportsBettingService() {
        this.builder = new DataBuilder();
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
        List<OutcomeOdd> winnerOutcomeOdds = new ArrayList<>();

        //kiválasztja a nyerő kimeneteleket
        chooseWinningOutcomes(winnerOutcomes, winnerOutcomeOdds);

        //kiválasztja a nyerő oddsokat a kimeneteleken belül
        for (OutcomeOdd o : winnerOutcomeOdds){
            for (Wager w : wagers){
                if (w.getOdd() == o){
                    w.setWin(true);
                    BigDecimal newBalance = w.getPlayer().getBalance().add(w.getOdd().getValue().multiply(w.getAmount()));
                    w.getPlayer().setBalance(newBalance);
                }
            }
        }
    }

    void chooseWinningOutcomes(List<Outcome> winnerOutcomes, List<OutcomeOdd> winnerOutcomeOdds){
        for (SportEvent se : this.builder.getEvents()){
            for (Bet b : se.getBets()){
                for (Outcome o : b.getOutcomes()){
                    if(new Random().nextBoolean()){
                        winnerOutcomeOdds.add(o.getOutcomeOdds().get(new Random().nextInt(o.getOutcomeOdds().size())));
                        winnerOutcomes.add(o);
                        break;
                    }
                }
            }
            se.setResult(new Result(winnerOutcomes));
        }
    }
}
