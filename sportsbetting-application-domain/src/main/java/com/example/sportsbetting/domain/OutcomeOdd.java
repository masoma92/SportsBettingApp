package com.example.sportsbetting.domain;

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

    public OutcomeOdd(OutcomeOddBuilder oob) {
        this.value = oob.value;
        this.validFrom = oob.validFrom;
        this.validUntil = oob.validUntil;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return "Actual odd: " + value + ", Valid between: " + validFrom.toString() + ", Valid until: " + validUntil.toString() + "\n";
    }

    public static class OutcomeOddBuilder {
        private BigDecimal value; //winning*
        private LocalDateTime validFrom; //this value valid from
        private LocalDateTime validUntil; //this value valid until

        public OutcomeOddBuilder() {
        }

        public OutcomeOddBuilder setValue(BigDecimal value){
            this.value = value;
            return this;
        }

        public OutcomeOddBuilder setValidFrom(LocalDateTime validFrom){
            this.validFrom = validFrom;
            return this;
        }

        public OutcomeOddBuilder setValidUntil(LocalDateTime validUntil){
            this.validUntil = validUntil;
            return this;
        }

        public OutcomeOdd getOutcomeOdd(){
            return new OutcomeOdd(this);
        }
    }
}
