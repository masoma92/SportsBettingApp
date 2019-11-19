package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.SportEventBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("football_event") //will appear in parent class eventtype field, if doesn't given its using classname
public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public FootballSportEvent(SportEventBuilder builder) {
        super(builder);
    }
}
