package domain;

import domain.builders.WagerBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Wager {
    private BigDecimal amount;
    private LocalDateTime timestampCreated; //when the wager created
    private boolean processed; //true if it has paid
    private boolean win; //true if player has won
    private Player player;
    private OutcomeOdd odd;
    private Currency currency;

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

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestampCreated() {
        return timestampCreated;
    }

    public boolean isProcessed() {
        return processed;
    }

    public boolean isWin() {
        return win;
    }

    public Player getPlayer() {
        return player;
    }

    public OutcomeOdd getOdd() {
        return odd;
    }

    public Currency getCurrency() {
        return currency;
    }
}
