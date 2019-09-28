package Model.Builders;

import Model.Currency;
import Model.OutcomeOdd;
import Model.Player;
import Model.Wager;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WagerBuilder {
    BigDecimal amount;
    LocalDateTime timestampCreated; //when the wager created
    boolean processed; //true if it has paid
    boolean win; //true if player has won
    Player player;
    OutcomeOdd odd;
    Currency currency;

    public WagerBuilder() {
        this.timestampCreated = LocalDateTime.now();
        this.processed = false;
        this.win = false;
    }

    public WagerBuilder setAmout(BigDecimal amount){
        this.amount=amount;
        return this;
    }
    public WagerBuilder setPlayer(Player player){
        this.player = player;
        return this;
    }
    public WagerBuilder setOutcomeOdd(OutcomeOdd outcomeOdd){
        this.odd = outcomeOdd;
        return this;
    }
    public WagerBuilder setCurrency(Currency curr){
        this.currency=curr;
        return this;
    }
    public Wager getWager(){
        return new Wager(this);
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
