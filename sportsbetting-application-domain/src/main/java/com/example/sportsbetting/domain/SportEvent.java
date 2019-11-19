package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.SportEventBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //all children goes in one entity
@DiscriminatorColumn(name = "sportevent_type") //children type
public abstract class SportEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    final private String title;

    @Column(name = "start_date")
    final private LocalDateTime startDate;

    @Column(name = "end_date")
    final private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "result_id")
    private Result result;

    @OneToMany(mappedBy = "event")
    private List<Bet> bets;

    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title; //sport event name
        this.startDate = startDate; //sport event start date
        this.endDate = endDate; //sport event end date
        this.bets = new ArrayList<>(); //list of the different type of bets
        for(Bet b : bets){
            b.setEvent(this);
        }
    }

    public SportEvent(SportEventBuilder builder) {
        this.title = builder.getTitle(); //sport event name
        this.startDate = builder.getStartDate(); //sport event start date
        this.endDate = builder.getEndDate(); //sport event end date
        this.bets = builder.getBets(); //list of the different type of bets
        for(Bet b : bets){
            b.setEvent(this);
        }
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public Result getResult() {
        return result;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void addBet(Bet bet){
        bet.setEvent(this);
        this.bets.add(bet);
    }



    @Override
    public String toString() {
        String output = "";
        int i = 0;

        for (Bet b : bets){
            for (Outcome o : b.getOutcomes()){
                for (OutcomeOdd od : o.getOutcomeOdds()){
                    output += ++i + ": Sport event: " + this.title + ", " + b.toString() + ", " + o.toString() + od.toString();
                }
            }
        }

        return output;
    }
}
