package com.example.sportsbetting.domain;

import com.example.sportsbetting.domain.builders.PlayerBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User{
    static public int accNum = 0;
    static public int getAccountNumber(){
        return accNum++;
    }

    private String name;
    private Integer accountNumber;
    private BigDecimal balance;
    private LocalDate birth;
    private Currency currency;

    public Player(String email, String password, String name, LocalDate birth, Currency currency) {
        super(email, password);
        this.name = name;
        this.balance = BigDecimal.valueOf(0.0);
        this.accountNumber = this.getAccountNumber();
        this.birth = birth;
        this.currency = currency;
    }

    public Player(PlayerBuilder builder){
        super(builder.getEmail(), builder.getPassword());
        this.name = builder.getName();
        this.accountNumber = this.getAccountNumber();
        this.balance = builder.getBalance();
        this.birth = builder.getBirth();
        this.currency = builder.getCurrency();
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
