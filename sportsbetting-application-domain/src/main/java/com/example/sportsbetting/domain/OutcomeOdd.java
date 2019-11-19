package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.OutcomeOddBuilder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class OutcomeOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    final private BigDecimal value; //winning*

    @Column(name = "valid_from")
    private LocalDateTime validFrom; //this value valid from

    @Column(name = "valid_until")
    private LocalDateTime validUntil; //this value valid until

    @ManyToOne
    @JoinColumn(name = "outcome_id")
    private Outcome outcome;

    public OutcomeOdd(BigDecimal value, LocalDateTime validFrom, LocalDateTime validUntil) {
        this.value = value;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
    }

    public OutcomeOdd(OutcomeOddBuilder oob) {
        this.value = oob.getValue();
        this.validFrom = oob.getValidFrom();
        this.validUntil = oob.getValidUntil();
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Actual odd: " + value + ", Valid between: " + validFrom.toString() + ", Valid until: " + validUntil.toString() + "\n";
    }
}
