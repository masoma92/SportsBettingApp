package com.example.sportsbetting.domain;


import com.example.sportsbetting.domain.builders.OutcomeBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Outcome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    final private String description; //eg.: 0 goal or 1 goal etc.

    //mappolva van Betben
    @ManyToOne
    private Bet bet;

    @OneToMany(mappedBy = "outcome")
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(String description, Bet bet, List<OutcomeOdd> outcomeOdds) throws OutcomeOddException {
        this.description = description;
        this.bet = bet;
        this.outcomeOdds = outcomeOdds;

        for (OutcomeOdd k : outcomeOdds){
            k.setOutcome(this);
        }
    }

    public Outcome(OutcomeBuilder builder) throws OutcomeOddException {
        this.description = builder.getDescription();
        this.bet = builder.getBet();
        this.outcomeOdds = builder.getOutcomeOdds();

        for (OutcomeOdd k : outcomeOdds){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addOutcomeOdds(OutcomeOdd outcomeOdd){
        this.outcomeOdds.add(outcomeOdd);
    }

    @Override
    public String toString() {
        return "Outcome: " + this.description + ", ";
    }
}
