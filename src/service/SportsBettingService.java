package service;

import domain.*;
import domain.builders.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SportsBettingService {

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
        try {
        LocalDateTime matchStartDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
        LocalDateTime matchEndDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

        //test for invalid interval
        //LocalDateTime test = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 39, 00);
        //LocalDateTime test2 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 50, 00);
        //OutcomeOdd testoo = new OutcomeOddBuilder().setValue(new BigDecimal(1.7)).setValidFrom(test).setValidUntil(test2).getOutcomeOdd();

        BetBuilder bb = new BetBuilder();
        BetBuilder bb2 = new BetBuilder();
        OutcomeBuilder ocb = new OutcomeBuilder();
        OutcomeBuilder ocb2 = new OutcomeBuilder();
        OutcomeBuilder ocb3 = new OutcomeBuilder();
        OutcomeBuilder ocb4 = new OutcomeBuilder();
        OutcomeOddBuilder oob = new OutcomeOddBuilder();

        OutcomeOdd oo = oob.setValue(new BigDecimal(1.5))
                .setValidFrom(matchStartDate)
                .setValidUntil(matchEndDate)
                .getOutcomeOdd();

        OutcomeOdd oo2 = oob.setValue(new BigDecimal(2))
                .setValidFrom(matchStartDate)
                .setValidUntil(matchEndDate)
                .getOutcomeOdd();

        OutcomeOdd oo3 = oob.setValue(new BigDecimal(2.5))
                .setValidFrom(matchStartDate)
                .setValidUntil(matchEndDate)
                .getOutcomeOdd();

        OutcomeOdd oo4 = oob.setValue(new BigDecimal(3))
                .setValidFrom(matchStartDate)
                .setValidUntil(matchEndDate)
                .getOutcomeOdd();


            Outcome oc = ocb.setDescription("Borussia Dortmund")
                    .addOutcomeOdd(oo)
                    //.addOutcomeOdd(testoo) test for invalid interval
                    .getOutcome();
            Outcome oc2 = ocb2.setDescription("Salvia Praha")
                    .addOutcomeOdd(oo2)
                    .getOutcome();
            Outcome oc3 = ocb3.setDescription("3 goals")
                    .addOutcomeOdd(oo3)
                    .getOutcome();
            Outcome oc4 = ocb4.setDescription("4 goals")
                    .addOutcomeOdd(oo4)
                    .getOutcome();

            Bet bet1 = bb.setDescription("winner")
                    .setType(BetType.WINNER)
                    .addOutcome(oc)
                    .addOutcome(oc2)
                    .getBet();

            Bet bet2 = bb2.setDescription("number of goals")
                    .setType(BetType.GOALS)
                    .addOutcome(oc3)
                    .addOutcome(oc4)
                    .getBet();

            SportEvent fse = new SportEventBuilder()
                    .setTitle("Salvia Praha vs Borussia Dortmund")
                    .setStartDate(matchStartDate)
                    .setEndDate(matchEndDate)
                    .getSportEvent("FootballSportEvent");

            fse.addBet(bet1);
            fse.addBet(bet2);

            this.builder.addEvent(fse);
        }
        catch (OutcomeOddException e){
            System.out.println(e.getMessage());
        }
        return builder.getEvents();
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

        Random rand = new Random();

        List<Outcome> winnerOutcomes = new ArrayList<>();
        List<OutcomeOdd> winnerOutcomeOdds = new ArrayList<>();

        //kiválasztja a nyerő kimeneteleket
        for (SportEvent se : this.builder.getEvents()){
            for (Bet b : se.getBets()){
                for (Outcome o : b.getOutcomes()){
                    if(rand.nextInt(1) == 0){
                        winnerOutcomeOdds.add(o.getOutcomeOdds().get(rand.nextInt(o.getOutcomeOdds().size())));
                        winnerOutcomes.add(o);
                        break;
                    }
                }
            }
            se.setResult(new Result(winnerOutcomes));
        }

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
}
