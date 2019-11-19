package com.example.sportsbetting.domain.builders;

import com.example.sportsbetting.domain.Currency;
import com.example.sportsbetting.domain.Player;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PlayerBuilder {
    private String name;
    private int accountNumber;
    private LocalDate birth;
    private Currency currency;
    private BigDecimal balance;
    private String email;
    private String password;

    public PlayerBuilder() {
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PlayerBuilder setName(String name){
        this.name = name;
        return this;
    }

    public PlayerBuilder setBirthDate(LocalDate birth){
        this.birth = birth;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public PlayerBuilder setCurrency(Currency currency){
        this.currency = currency;
        return this;
    }

    public PlayerBuilder setEmail(String email){
        this.email = email;
        return this;
    }

    public PlayerBuilder setPassword(String password){
        this.password = password;
        return this;
    }

    public PlayerBuilder setBalance(BigDecimal balance){
        this.balance = balance;
        return this;
    }

    public PlayerBuilder setAccountNumber(int accountNumber){
        this.accountNumber = accountNumber;
        return this;
    }

    public int getAccountNumber(){
        return this.accountNumber;
    }

    public Player getPlayer(){
        return new Player(this);
    }
}
