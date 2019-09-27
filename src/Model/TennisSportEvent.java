package Model;

import java.time.LocalDateTime;
import java.util.List;

public class TennisSportEvent extends SportEvent {


    public TennisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate) {
        super(title, startDate, endDate);
    }
}
