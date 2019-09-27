package Model;

import Application.App;
import Model.Builders.PlayerBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Player extends User{
    String name;
    Integer accountNumber;
    BigDecimal balance;
    LocalDate birth;
    Currency currency;

    public Player(String email, String password, String name, LocalDate birth, Currency currency) {
        super(email, password);
        this.name = name;
        this.balance = BigDecimal.valueOf(0.0);
        this.accountNumber = App.getAccountNumber();
        this.birth = birth;
        this.currency = currency;
    }

    public Player(PlayerBuilder builder){
        super(builder.getEmail(), builder.getPassword());
        this.name = builder.getName();
        this.accountNumber = App.getAccountNumber();
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
}
