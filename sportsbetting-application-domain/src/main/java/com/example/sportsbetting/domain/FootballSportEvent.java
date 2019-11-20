package com.example.sportsbetting.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("football_event") //will appear in parent class eventtype field, if doesn't given its using classname
public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(SportEventBuilder builder) {
        super(builder);
    }

}
