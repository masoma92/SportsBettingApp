package Model;

import Model.Builders.WagerBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
    BigDecimal amount;
    LocalDateTime timestampCreated; //when the wager created
    boolean processed; //true if it has paid
    boolean win; //true if player has won
    Player player;
    OutcomeOdd odd;
    Currency currency;

    public Wager(BigDecimal amount, Player player, OutcomeOdd odd, Currency currency) {
        this.amount = amount;
        this.timestampCreated = LocalDateTime.now();
        this.processed = false;
        this.win = false;
        this.player = player;
        this.odd = odd;
        this.currency = currency;
    }

    public Wager(WagerBuilder builder) {
        this.amount = builder.getAmount();
        this.timestampCreated = builder.getTimestampCreated();
        this.processed = builder.isProcessed();
        this.win = builder.isWin();
        this.player = builder.getPlayer();
        this.odd = builder.getOdd();
        this.currency = builder.getCurrency();
    }


}
