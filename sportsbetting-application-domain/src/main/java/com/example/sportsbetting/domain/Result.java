package com.example.sportsbetting.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public final class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    List<Outcome> winnerOutcomes; //1 event all outcomes

    public Result() {
    }

    public Result(List<Outcome> winnerOutcomes){
        this.winnerOutcomes = winnerOutcomes;
    }

    public List<Outcome> getWinnerOutcomes() {
        return winnerOutcomes;
    }
}
