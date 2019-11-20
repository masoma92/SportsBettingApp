package com.example.sportsbetting.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("tennis_event") //will appear in parent class eventtype field, if doesn't given its using classname
public class TennisSportEvent extends SportEvent {

    public TennisSportEvent(SportEventBuilder builder) {
        super(builder);
    }

}
