package Model.Builders;

import Model.Bet;
import Model.BetType;
import Model.Outcome;
import Model.SportEvent;

import java.util.ArrayList;
import java.util.List;

public class BetBuilder {

    String description; //eg.:number of goals
    BetType type;
    SportEvent event;
    List<Outcome> outcomes; //eg.: number of goals then 0 or 1 or more than 1

    public BetBuilder() {
        this.outcomes = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public BetType getType() {
        return type;
    }

    public SportEvent getEvent() {
        return event;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public BetBuilder addOutcome(Outcome outcome){
        this.outcomes.add(outcome);
        return this;
    }

    public BetBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public BetBuilder setType(BetType type){
        this.type = type;
        return this;
    }

    public BetBuilder setEvent(SportEvent event){
        this.event = event;
        return this;
    }

    public Bet getBet(){
        return new Bet(this);
    }
}
