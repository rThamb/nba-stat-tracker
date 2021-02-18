package data;

import model.PlayerStat;

import java.util.List;

public interface PlayerRepo {

    public List<PlayerStat> getPlayers();
}
