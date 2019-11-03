package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.SportEventBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public FootballSportEvent(SportEventBuilder builder) {
        super(builder);
    }
}
