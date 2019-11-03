package com.example.sportsbetting.domain.builders;

import com.example.sportsbetting.domain.Bet;
import com.example.sportsbetting.domain.FootballSportEvent;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.TennisSportEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SportEventBuilder {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Bet> bets;

    public SportEventBuilder() {
        this.bets = new ArrayList<>();
    }

    public void addBet(Bet bet){
        this.bets.add(bet);
    }

    public List<Bet> getBets() {
        return bets;
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
