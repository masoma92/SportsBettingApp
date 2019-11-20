package com.example.sportsbetting.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Wager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    final private BigDecimal amount;

    @Column(name = "timestamp_created")
    final private LocalDateTime timestampCreated; //when the wager created

    private boolean processed; //true if it has paid

    private boolean win; //true if player has won

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "odd_id")
    private OutcomeOdd odd;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Wager(WagerBuilder builder) {
        this.amount = builder.amount;
        this.timestampCreated = builder.timestampCreated;
        this.processed = builder.processed;
        this.win = builder.win;
        this.player = builder.player;
        this.odd = builder.odd;
        this.currency = builder.currency;
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

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public static class WagerBuilder {
        private BigDecimal amount;
        private LocalDateTime timestampCreated; //when the wager created
        private boolean processed; //true if it has paid
        private boolean win; //true if player has won
        private Player player;
        private OutcomeOdd odd;
        private Currency currency;

        public WagerBuilder() {
            this.timestampCreated = LocalDateTime.now();
            this.processed = false;
            this.win = false;
        }

        public WagerBuilder setAmount(BigDecimal amount){
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
    }
}
