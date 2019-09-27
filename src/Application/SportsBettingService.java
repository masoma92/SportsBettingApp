package Application;

import Model.*;
import Model.Builders.BetBuilder;
import Model.Builders.DataBuilder;
import Model.Builders.OutcomeBuilder;
import Model.Builders.OutcomeOddBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class SportsBettingService {

    DataBuilder builder;

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

    public List<SportEvent> findAllSportEvents(){
        /*LocalDateTime matchStartDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
        LocalDateTime matchEndDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

        LocalDateTime oddStartDate1 = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
        LocalDateTime oddEndDate1 = LocalDateTime.of(2019, Month.OCTOBER, 02, 19, 10, 00);

        LocalDateTime oddStartDate2 = LocalDateTime.of(2019, Month.OCTOBER, 02, 19, 11, 00);
        LocalDateTime oddEndDate2 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 00, 00);

        LocalDateTime oddStartDate3 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 01, 00);
        LocalDateTime oddEndDate3 = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

        OutcomeOdd ocob = new OutcomeOdd(new BigDecimal(1.5), oddStartDate1, oddEndDate1);
        OutcomeOdd ocob2 = new OutcomeOdd(new BigDecimal(1.2), oddStartDate1, oddEndDate1);
        OutcomeOdd ocob3 = new OutcomeOdd(new BigDecimal(1.05), oddStartDate1, oddEndDate1);
        List<OutcomeOdd> outcomeOddsDortmundWin = new ArrayList<OutcomeOdd>();
        outcomeOddsDortmundWin.add(ocob);
        outcomeOddsDortmundWin.add(ocob2);
        outcomeOddsDortmundWin.add(ocob3);

        OutcomeOdd ocob4 = new OutcomeOdd(new BigDecimal(4), matchEndDate, matchEndDate);
        List<OutcomeOdd> outcomeOddsSalviaWin = new ArrayList<>();
        outcomeOddsSalviaWin.add(ocob4);

        BetBuilder betb = new BetBuilder();
        betb = betb.setDescription("winner").setType(BetType.WINNER);

        Outcome o = new Outcome("Borussia Dortmund", betb.getBet(), outcomeOddsDortmundWin);
        Outcome o2 = new Outcome("Salvia Praha", betb.getBet(), outcomeOddsSalviaWin);

        betb.addOutcome(o);
        betb.addOutcome(o2);

        List<Bet> bets = new ArrayList<>();
        bets.add(betb.getBet());

        FootballSportEvent fse = new FootballSportEvent("Salvia Praha vs Borussia Dortmund", matchStartDate, matchEndDate, bets);

        this.builder.addEvent(fse);

        return this.builder.getEvents();*/

        LocalDateTime matchStartDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 18, 55, 00);
        LocalDateTime matchEndDate = LocalDateTime.of(2019, Month.OCTOBER, 02, 20, 40, 00);

        OutcomeOddBuilder oob = new OutcomeOddBuilder();
        OutcomeOdd oo = oob.setValue(new BigDecimal(1.5))
                .setValidFrom(matchStartDate)
                .setValidUntil(matchEndDate)
                .getOutcomeOdd();

        BetBuilder bb = new BetBuilder();
        bb.setDescription("winner")
                .setType(BetType.WINNER);

        OutcomeBuilder ocb = new OutcomeBuilder();
        Outcome oc = ocb.setDescription("Borussia Dortmund")
                .setBet(bb.getBet())
                .addOutcomeOdd(oo)
                .getOutcome();

        Bet b = bb.addOutcome(oc).getBet();

        FootballSportEvent fse = new FootballSportEvent("Salvia Praha vs Borussia Dortmund", matchStartDate, matchEndDate);
        fse.addBet(b);

        this.builder.addEvent(fse);
        return builder.getEvents();
    }
}
