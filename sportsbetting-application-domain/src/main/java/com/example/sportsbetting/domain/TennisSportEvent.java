package com.example.sportsbetting.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("tennis_event") //will appear in parent class eventtype field, if doesn't given its using classname
public class TennisSportEvent extends SportEvent {

    public TennisSportEvent(){

    }

    public TennisSportEvent(SportEventBuilder builder) {
        super(builder);
    }

    @Override
    public String getType() {
        return "Tennis match";
    }
}
