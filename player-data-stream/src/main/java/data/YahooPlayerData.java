package data;

import model.PlayerStat;

import java.util.ArrayList;
import java.util.List;

public class YahooPlayerData implements PlayerRepo{
    @Override
    public List<PlayerStat> getPlayers() {
        List data = new ArrayList();
        PlayerStat p = new PlayerStat();
        p.setFname("Kyrie");
        p.setLname("Irving");

        data.add(p);

        return data;
    }
}
