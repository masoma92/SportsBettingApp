package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.PlayerBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Player extends User{

    @Column(name = "account_number")
    private int accountNumber;

    final private String name;

    private BigDecimal balance;

    final private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Player(String email, String password, String name, LocalDate birth, Currency currency) {
        super(email, password);
        this.name = name;
        this.balance = BigDecimal.valueOf(0.0);
        this.birth = birth;
        this.currency = currency;
    }

    public Player(PlayerBuilder builder){
        super(builder.getEmail(), builder.getPassword());
        this.name = builder.getName();
        this.balance = builder.getBalance();
        this.birth = builder.getBirth();
        this.currency = builder.getCurrency();
        this.accountNumber = builder.getAccountNumber();
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
