package com.example.sportsbetting.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description; //eg.:number of goals

    @Enumerated(EnumType.STRING)
    private BetType type;

    //mapped in SportEvent
    @ManyToOne
    @JoinColumn(name = "event_id")
    private SportEvent event;

    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Outcome> outcomes; //eg.: number of goals then 0 or 1 or more than 1

    public Bet(){

    }
    
    public Bet(BetBuilder builder) {
        this.description = builder.description;
        this.type = builder.type;
        this.outcomes = builder.outcomes;
        for (Outcome k : outcomes){
            k.setBet(this);
        }
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

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Bet: " + this.description;
    }

    public static class BetBuilder {

        private String description; //eg.:number of goals
        private BetType type;
        private List<Outcome> outcomes; //eg.: number of goals then 0 or 1 or more than 1

        public BetBuilder() {
            this.outcomes = new ArrayList<>();
        }

        public BetBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public BetBuilder setType(BetType type){
            this.type = type;
            return this;
        }

        public BetBuilder addOutcome(Outcome outcome){
            this.outcomes.add(outcome);
            return this;
        }

        public Bet getBet(){
            return new Bet(this);
        }
    }
}
