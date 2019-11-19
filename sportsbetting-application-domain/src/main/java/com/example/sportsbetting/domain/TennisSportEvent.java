package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.SportEventBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("tennis_event") //will appear in parent class eventtype field, if doesn't given its using classname
public class TennisSportEvent extends SportEvent {


    public TennisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public TennisSportEvent(SportEventBuilder builder) {
        super(builder);
    }
}
