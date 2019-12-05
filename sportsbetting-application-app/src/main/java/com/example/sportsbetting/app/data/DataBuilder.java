package com.example.sportsbetting.app.data;

import com.example.sportsbetting.domain.*;
import com.example.sportsbetting.repository.PlayerRepository;
import com.example.sportsbetting.repository.SportEventRepository;
import com.example.sportsbetting.repository.WagerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

public class DataBuilder {

    @Autowired
    private List<SportEvent> events;
    @Autowired
    private Player player;
    @Autowired
    private List<Wager> wagers;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SportEventRepository sportEventRepository;

    @Autowired
    private WagerRepository wagerRepository;

    public DataBuilder() {
    }

    public void buildingData(){
        try {
            LocalDateTime matchStartDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
            LocalDateTime matchEndDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

            LocalDateTime matchStartDate2 = LocalDateTime.of(2019, Month.DECEMBER, 11, 21, 00, 00);
            LocalDateTime matchEndDate2 = LocalDateTime.of(2019, Month.DECEMBER, 11, 22, 45, 00);

            //test for invalid interval
            //LocalDateTime test = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 39, 00);
            //LocalDateTime test2 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 50, 00);
            //OutcomeOdd testoo = new OutcomeOddBuilder().setValue(new BigDecimal(1.7)).setValidFrom(test).setValidUntil(test2).getOutcomeOdd();

            OutcomeOdd oo = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(1.5))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo2 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(2))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo3 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(2.5))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo4 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(3))
                    .setValidFrom(matchStartDate)
                    .setValidUntil(matchEndDate)
                    .getOutcomeOdd();

            OutcomeOdd oo5 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(2))
                    .setValidFrom(matchStartDate2)
                    .setValidUntil(matchEndDate2)
                    .getOutcomeOdd();

            OutcomeOdd oo6 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(1.5))
                    .setValidFrom(matchStartDate2)
                    .setValidUntil(matchEndDate2)
                    .getOutcomeOdd();

            OutcomeOdd oo7 = new OutcomeOdd.OutcomeOddBuilder().setValue(new BigDecimal(3))
                    .setValidFrom(matchStartDate2)
                    .setValidUntil(matchEndDate2)
                    .getOutcomeOdd();


            Outcome oc = new Outcome.OutcomeBuilder().setDescription("Borussia Dortmund")
                    .addOutcomeOdd(oo)
                    //.addOutcomeOdd(testoo) test for invalid interval
                    .getOutcome();
            Outcome oc2 = new Outcome.OutcomeBuilder().setDescription("Salvia Praha")
                    .addOutcomeOdd(oo2)
                    .getOutcome();
            Outcome oc3 = new Outcome.OutcomeBuilder().setDescription("3 goals")
                    .addOutcomeOdd(oo3)
                    .getOutcome();
            Outcome oc4 = new Outcome.OutcomeBuilder().setDescription("4 goals")
                    .addOutcomeOdd(oo4)
                    .getOutcome();

            Outcome oc5 = new Outcome.OutcomeBuilder().setDescription("Robert Lewandowski scores a goal")
                    .addOutcomeOdd(oo5)
                    .getOutcome();
            Outcome oc6 = new Outcome.OutcomeBuilder().setDescription("Bayern München")
                    .addOutcomeOdd(oo6)
                    .getOutcome();
            Outcome oc7 = new Outcome.OutcomeBuilder().setDescription("Tottenham")
                    .addOutcomeOdd(oo7)
                    .getOutcome();

            dateValidation(oc.getOutcomeOdds());
            dateValidation(oc2.getOutcomeOdds());
            dateValidation(oc3.getOutcomeOdds());
            dateValidation(oc4.getOutcomeOdds());
            dateValidation(oc5.getOutcomeOdds());
            dateValidation(oc6.getOutcomeOdds());
            dateValidation(oc7.getOutcomeOdds());

            Bet bet1 = new Bet.BetBuilder().setDescription("winner")
                    .setType(BetType.WINNER)
                    .addOutcome(oc)
                    .addOutcome(oc2)
                    .getBet();

            Bet bet2 = new Bet.BetBuilder().setDescription("number of goals")
                    .setType(BetType.GOALS)
                    .addOutcome(oc3)
                    .addOutcome(oc4)
                    .getBet();

            Bet bet3 = new Bet.BetBuilder().setDescription("player's score")
                    .setType(BetType.PLAYERS_SCORE)
                    .addOutcome(oc5)
                    .getBet();

            Bet bet4 = new Bet.BetBuilder().setDescription("winner")
                    .setType(BetType.WINNER)
                    .addOutcome(oc6)
                    .addOutcome(oc7)
                    .getBet();

            SportEvent fse = new SportEvent.SportEventBuilder()
                    .setTitle("Salvia Praha vs Borussia Dortmund")
                    .setStartDate(matchStartDate)
                    .setEndDate(matchEndDate)
                    .getSportEvent("FootballSportEvent");

            SportEvent fse2 = new SportEvent.SportEventBuilder()
                    .setTitle("Bayern München vs Tottenham")
                    .setStartDate(matchStartDate2)
                    .setEndDate(matchEndDate2)
                    .getSportEvent("FootballSportEvent");

            fse.addBet(bet1);
            fse.addBet(bet2);

            fse2.addBet(bet3);
            fse2.addBet(bet4);

            this.addEvent(fse);
            this.addEvent(fse2);

            //for web!
            this.player = new Player.PlayerBuilder()
                    .setEmail("user@oe.hu")
                    .setPassword("almafa")
                    .setBalance(new BigDecimal(0)).getPlayer();

            this.playerRepository.save(player);

            this.sportEventRepository.saveAll(this.events);

            this.wagers.add(new Wager.WagerBuilder().setPlayer(player).setAmount(BigDecimal.valueOf(200)).setOutcomeOdd(oo).setCurrency(Currency.HUF).getWager());
            this.wagers.add(new Wager.WagerBuilder().setPlayer(player).setAmount(BigDecimal.valueOf(200)).setOutcomeOdd(oo2).setCurrency(Currency.HUF).getWager());
            this.wagers.add(new Wager.WagerBuilder().setPlayer(player).setAmount(BigDecimal.valueOf(200)).setOutcomeOdd(oo4).setCurrency(Currency.HUF).getWager());
            this.wagers.add(new Wager.WagerBuilder().setPlayer(player).setAmount(BigDecimal.valueOf(200)).setOutcomeOdd(oo5).setCurrency(Currency.HUF).getWager());
            this.wagers.add(new Wager.WagerBuilder().setPlayer(player).setAmount(BigDecimal.valueOf(200)).setOutcomeOdd(oo6).setCurrency(Currency.HUF).getWager());

            wagerRepository.saveAll(wagers);
        }
        catch (OutcomeOddException e){
            System.out.println(e.getMessage());
        }
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

    public void setPlayer(Player player){
        this.player = player;
    }

    public List<Wager> getWagers() {
        return this.wagers;
    }

    public Wager getWagerById(int id){
        for (Wager w : wagers){
            if (w.getId() == id)
                return w;
        }
        return null;
    }

    void dateValidation(List<OutcomeOdd> outcomeOdds) throws OutcomeOddException {
        for (int i = 0; i < outcomeOdds.size(); i++){
            if (i < outcomeOdds.size()-1 && outcomeOdds.get(i).getValidUntil().isAfter(outcomeOdds.get(i+1).getValidFrom())){
                throw new OutcomeOddException(outcomeOdds.get(i), outcomeOdds.get(i+1));
            }
            outcomeOdds.get(i).setOutcome(outcomeOdds.get(i).getOutcome());
        }
    }
}
