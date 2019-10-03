package domain;


import domain.builders.OutcomeBuilder;

import java.util.List;

public class Outcome {
    private String description; //eg.: 0 goal or 1 goal etc.
    private Bet bet;
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(String description, Bet bet, List<OutcomeOdd> outcomeOdds) {
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;
        for (OutcomeOdd k : outcomeOdds) {
            k.setOutcome(this);
        }
    }

    public Outcome(OutcomeBuilder builder) {
        this.description = builder.getDescription();
        this.bet = builder.getBet();
        this.outcomeOdds = builder.getOutcomeOdds();
        for (OutcomeOdd k : outcomeOdds) {
            k.setOutcome(this);
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