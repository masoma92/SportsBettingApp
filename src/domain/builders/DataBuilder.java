package domain.builders;

import domain.*;

import java.util.ArrayList;
import java.util.List;

public class DataBuilder {

    private List<SportEvent> events;
    private Player player;
    private List<Wager> wagers;

    public DataBuilder() {
        events = new ArrayList<>();
        wagers = new ArrayList<>();
    }

    public DataBuilder setPlayer(Player player){
        this.player = player;
        return this;
    }

    public DataBuilder addWager(Wager wager){
        this.wagers.add(wager);
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

    public List<Wager> getWagers() {
        return wagers;
    }
}
