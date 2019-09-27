package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

;

public class Wager {
    BigDecimal amount;
    LocalDateTime timestampCreated; //when the wager created
    boolean processed; //true if it has paid
    boolean win; //true if player has won
    Player player;
    OutcomeOdd odd;
    Currency currency;

    public Wager(BigDecimal amount, LocalDateTime timestampCreated, boolean processed, boolean win, Player player, OutcomeOdd odd, Currency currency) {
        this.amount = amount;
        this.timestampCreated = timestampCreated;
        this.processed = processed;
        this.win = win;
        this.player = player;
        this.odd = odd;
        this.currency = currency;
    }
}
