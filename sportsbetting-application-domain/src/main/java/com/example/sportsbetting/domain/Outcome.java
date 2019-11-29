package com.example.sportsbetting.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description; //eg.: 0 goal or 1 goal etc.

    //mapped in Bet
    @ManyToOne
    private Bet bet;

    // cascadetype = persistence will propagate (kiterjeszt) to the relating entities
    @OneToMany(mappedBy = "outcome", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(){

    }

    public Outcome(OutcomeBuilder builder) throws OutcomeOddException {
        this.description = builder.description;
        this.outcomeOdds = builder.outcomeOdds;
        for (OutcomeOdd k : outcomeOdds){
            k.setOutcome(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public Bet getBet() {
        return bet;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    @Override
    public String toString() {
        return "Outcome: " + this.description + ", ";
    }

    public static class OutcomeBuilder {
        private String description; //eg.: 0 goal or 1 goal etc.
        private List<OutcomeOdd> outcomeOdds;

        public OutcomeBuilder() {
            this.outcomeOdds = new ArrayList<>();
        }

        public OutcomeBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public OutcomeBuilder addOutcomeOdd(OutcomeOdd oco){
            this.outcomeOdds.add(oco);
            return this;
        }

        public Outcome getOutcome() throws OutcomeOddException {
            return new Outcome(this);
        }
    }
}
