package Model.Builders;

import Model.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    List<SportEvent> events;
    Player player;
    Wager wager;

    public DataBuilder() {
        events = new ArrayList<>();
    }

    public DataBuilder setPlayer(Player player){
        this.player = player;
        return this;
    }

    public DataBuilder setWager(Wager player){
        this.wager = wager;
        return this;
    }

    public DataBuilder addEvent(SportEvent event){
        this.events.add(event);
        return this;
    }

    public List<SportEvent> getEvents() {
        return events;
    }

    public Player getPlayer() {
        return player;
    }
}
