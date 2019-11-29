package com.example.sportsbetting.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Player extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number")
    private String accountNumber;

    private String name;

    private BigDecimal balance;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Player(){

    }

    public Player(PlayerBuilder builder){
        super(builder.email, builder.password);
        this.name = builder.name;
        this.balance = builder.balance;
        this.birth = builder.birth;
        this.currency = builder.currency;
        this.accountNumber = builder.accountNumber;
    }

    public String getAccountNumber() {return accountNumber;}

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static class PlayerBuilder {
        private String name;
        private String accountNumber;
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

        public PlayerBuilder setName(String name){
            this.name = name;
            return this;
        }

        public PlayerBuilder setBirthDate(LocalDate birth){
            this.birth = birth;
            return this;
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

        public PlayerBuilder setAccountNumber(String accountNumber){
            this.accountNumber = accountNumber;
            return this;
        }

        public Player getPlayer(){
            return new Player(this);
        }
    }
}
