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
