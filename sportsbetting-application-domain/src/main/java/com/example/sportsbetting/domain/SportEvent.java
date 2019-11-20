package com.example.sportsbetting.domain;

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

    public SportEvent(SportEventBuilder builder) {
        this.title = builder.title; //sport event name
        this.startDate = builder.startDate; //sport event start date
        this.endDate = builder.endDate; //sport event end date
        this.bets = builder.bets; //list of the different type of bets
        for(Bet b : bets){
            b.setEvent(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public static class SportEventBuilder{
        private String title;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<Bet> bets;

        public SportEventBuilder() {
            this.bets = new ArrayList<>();
        }

        public SportEventBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public SportEventBuilder setStartDate(LocalDateTime startDate){
            this.startDate = startDate;
            return this;
        }

        public SportEventBuilder setEndDate(LocalDateTime endDate){
            this.endDate = endDate;
            return this;
        }

        public SportEvent getSportEvent(String sportEventType){
            switch (sportEventType)
            {
                case "FootballSportEvent":
                    return new FootballSportEvent(this);
                case "TennisSportEvent":
                    return new TennisSportEvent(this);
                default:
                    return null;
            }
        }
    }
}
