package domain;


import domain.builders.OutcomeBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class Outcome {
    private String description; //eg.: 0 goal or 1 goal etc.
    private Bet bet;
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(String description, Bet bet, List<OutcomeOdd> outcomeOdds) throws OutcomeOddException {
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;

        // checking if outcomeodds are in the same interval
        for (int i = 0; i < outcomeOdds.size(); i++){
            if (i < outcomeOdds.size()-1 && outcomeOdds.get(i).getValidUntil().isAfter(outcomeOdds.get(i+1).getValidFrom())){
                throw new OutcomeOddException(outcomeOdds.get(i), outcomeOdds.get(i+1));
            }
            outcomeOdds.get(i).setOutcome(this);
        }
    }

    public Outcome(OutcomeBuilder builder) throws OutcomeOddException {
        this.description = builder.getDescription();
        this.bet = builder.getBet();
        this.outcomeOdds = builder.getOutcomeOdds();

        // checking if outcomeodds are in the same interval
        for (int i = 0; i < outcomeOdds.size(); i++){
            if (i < outcomeOdds.size()-1 && outcomeOdds.get(i).getValidUntil().isAfter(outcomeOdds.get(i+1).getValidFrom())){
                throw new OutcomeOddException(outcomeOdds.get(i), outcomeOdds.get(i+1));
            }
            outcomeOdds.get(i).setOutcome(this);
        }
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public Bet getBet() {
        return bet;
    }

    public String getDescription() {
        return description;
    }



    public void addOutcomeOdds(OutcomeOdd outcomeOdd){
        this.outcomeOdds.add(outcomeOdd);
    }

    @Override
    public String toString() {
        return "Outcome: " + this.description + ", ";
    }
}
