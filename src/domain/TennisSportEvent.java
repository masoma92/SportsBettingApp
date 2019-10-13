package domain;

import domain.builders.SportEventBuilder;

import java.time.LocalDateTime;

public class TennisSportEvent extends SportEvent {


    public TennisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public TennisSportEvent(SportEventBuilder builder) {
        super(builder);
    }
}
