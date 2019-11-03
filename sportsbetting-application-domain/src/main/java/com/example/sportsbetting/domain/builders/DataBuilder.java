package com.example.sportsbetting.domain.builders;

import com.example.sportsbetting.domain.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    private List<SportEvent> events;
    private Player player;
    private List<Wager> wagers;

    public DataBuilder() {
        events = new ArrayList<>();
        wagers = new ArrayList<>();
    }

    void dateValidation(List<OutcomeOdd> outcomeOdds) throws OutcomeOddException{
        for (int i = 0; i < outcomeOdds.size(); i++){
            if (i < outcomeOdds.size()-1 && outcomeOdds.get(i).getValidUntil().isAfter(outcomeOdds.get(i+1).getValidFrom())){
                throw new OutcomeOddException(outcomeOdds.get(i), outcomeOdds.get(i+1));
            }
            outcomeOdds.get(i).setOutcome(outcomeOdds.get(i).getOutcome());
        }
    }

    public void buildingDatas(){
        try {
            LocalDateTime matchStartDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
            LocalDateTime matchEndDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

            //test for invalid interval
            //LocalDateTime test = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 39, 00);
            //LocalDateTime test2 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 50, 00);
            //OutcomeOdd testoo = new OutcomeOddBuilder().setValue(new BigDecimal(1.7)).setValidFrom(test).setValidUntil(test2).getOutcomeOdd();

            OutcomeOdd oo = new OutcomeOddBuilder().setValue(new BigDecimal(1.5))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo2 = new OutcomeOddBuilder().setValue(new BigDecimal(2))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo3 = new OutcomeOddBuilder().setValue(new BigDecimal(2.5))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo4 = new OutcomeOddBuilder().setValue(new BigDecimal(3))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();


            Outcome oc = new OutcomeBuilder().setDescription("Borussia Dortmund")
                    .addOutcomeOdd(oo)
                    //.addOutcomeOdd(testoo) test for invalid interval
                    .getOutcome();
            Outcome oc2 = new OutcomeBuilder().setDescription("Salvia Praha")
                    .addOutcomeOdd(oo2)
                    .getOutcome();
            Outcome oc3 = new OutcomeBuilder().setDescription("3 goals")
                    .addOutcomeOdd(oo3)
                    .getOutcome();
            Outcome oc4 = new OutcomeBuilder().setDescription("4 goals")
                    .addOutcomeOdd(oo4)
                    .getOutcome();

            dateValidation(oc.getOutcomeOdds());
            dateValidation(oc2.getOutcomeOdds());
            dateValidation(oc3.getOutcomeOdds());
            dateValidation(oc4.getOutcomeOdds());

            Bet bet1 = new BetBuilder().setDescription("winner")
                    .setType(BetType.WINNER)
                    .addOutcome(oc)
                    .addOutcome(oc2)
                    .getBet();

            Bet bet2 = new BetBuilder().setDescription("number of goals")
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

            this.addEvent(fse);
        }
        catch (OutcomeOddException e){
            System.out.println(e.getMessage());
        }
    }

    public DataBuilder setPlayer(Player player){
        this.player = player;
        return this;
    }

    public DataBuilder addWager(Wager wager){
        this.wagers.add(wager);
        return this;
    }

    public DataBuilder addEvent(SportEvent event){
        this.events.add(event);
        return this;
    }

    public List<SportEvent> getEvents() {
        return events;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Wager> getWagers() {
        return wagers;
    }
}
