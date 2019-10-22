package domain;

import domain.builders.BetBuilder;

import java.util.List;


public class Bet {
    private String description; //eg.:number of goals
    private BetType type;
    private SportEvent event;
    private List<Outcome> outcomes; //eg.: number of goals then 0 or 1 or more than 1

    public Bet(String description, BetType type, List<Outcome> outcomes) {
        this.description = description;
        this.type = type;
        this.outcomes = outcomes;
        for (Outcome k : outcomes){
            k.setBet(this);
        }
    }

    public Bet(BetBuilder builder) {
        this.description = builder.getDescription();
        this.type = builder.getType();
        this.event = builder.getEvent();
        this.outcomes = builder.getOutcomes();
        for (Outcome k : outcomes){
            k.setBet(this);
        }
    }

    public SportEvent getEvent() {
        return event;
    }

    public String getDescription() {
        return description;
    }

    public BetType getType() {
        return type;
    }

    public void addOutCome(Outcome outcome){
        this.outcomes.add(outcome);
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Bet: " + this.description;
    }
}
