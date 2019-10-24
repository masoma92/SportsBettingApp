package domain;

import domain.builders.SportEventBuilder;

import java.time.LocalDateTime;

public class FootballSportEvent extends SportEvent {

    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public FootballSportEvent(SportEventBuilder builder) {
        super(builder);
    }
}
