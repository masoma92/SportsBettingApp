package com.example.sportsbetting.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Player extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "account_number")
    private int accountNumber;

    final private String name;

    private BigDecimal balance;

    final private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Player(PlayerBuilder builder){
        super(builder.email, builder.password);
        this.name = builder.name;
        this.balance = builder.balance;
        this.birth = builder.birth;
        this.currency = builder.currency;
        this.accountNumber = builder.accountNumber;
    }

    public int getAccountNumber() {return accountNumber;}

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

    public static class PlayerBuilder {
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

        public PlayerBuilder setAccountNumber(int accountNumber){
            this.accountNumber = accountNumber;
            return this;
        }

        public Player getPlayer(){
            return new Player(this);
        }
    }
}
