package analysis;

import model.PlayerStat;

import java.util.ArrayList;
import java.util.List;

public class StatAnalyzer {

    private List<PlayerStat> players;

    public StatAnalyzer(List<PlayerStat> players){
        this.players = players;
    }

    public List<PlayerStat> getGuardRanks(){
        PlayerStat p = players.get(0);
        p.setRank(1);
        p.setPosition("PG,SG");
        return players;
    }


    public List<PlayerStat> getForwardRanks(){
        PlayerStat p = players.get(0);
        p.setRank(1);
        p.setPosition("PG,SG");
        return players;
    }

}
