package analysis;

import analysis.extractionStratgies.*;
import analysis.util.PlayerStatVector;
import analysis.util.StatDistribution;
import model.PlayerStat;

import java.util.*;
import java.util.stream.Collectors;

public class StatAnalyzer {

    //All NBA player data
    private List<PlayerStat> players;

    private StatDistribution assistDistribution;
    private StatDistribution blockDistribution;
    private StatDistribution pointDistribution;
    private StatDistribution rebDistribution;
    private StatDistribution stealDistribution;
    private StatDistribution threeDistribution;
    private StatDistribution toDistribution;

    // FG%, FT%, 3pts, pts, reb, ast, st, blk, to
    //Default entry = 1/ 9
    //USED TO PUT MORE WEIGHT ON CERTAIN STATS
    //private double[] amplifierArray = {0.1, 0.1, 0.02, 0.5, 0.2, 0.02, 0.02, 0.02, 0.02};
    private double[] amplifierArray;

    public StatAnalyzer(List<PlayerStat> players) throws Exception {
        this.players = players;
        setup();
    }


    private void setup() throws Exception {
        assistDistribution = buildIndex(new GetAssistsAvg());
        blockDistribution = buildIndex(new GetBlocksAvg());
        pointDistribution = buildIndex(new GetPointsAvg());
        rebDistribution = buildIndex(new GetRebAvg());
        stealDistribution = buildIndex(new GetStealsAvg());
        threeDistribution = buildIndex(new GetThreeAvg());
        toDistribution = buildIndex(new GetTOAvg());

        //ORDER MATTERS
        // FG%, FT%, 3pts, pts, reb, ast, st, blk, to
        //Default entry = 1/ 9
        //USED TO PUT MORE WEIGHT ON CERTAIN STATS
        double value = 1 / 9;
        //double[] amplifierArray = {0.0, 0.0, 0.0, 1, 0.0, 0.0, 0.0, 0.0, 0.0};
        double[] amplifierArray = {0.025, 0.025, 0.0, 0.6, 0.05, 0.3, 0.0, 0.0, 0.0};
        this.amplifierArray = amplifierArray;

        //check array
        double sum = 0;
        for(double e : amplifierArray)
            sum += e;

        if(sum != 1)
            throw new Exception("Amplifier array not equal to one");
    }

    private StatDistribution buildIndex(StatExtractionStategy extract){
        double[] data = new double[players.size()];
        for(int i =0; i < players.size(); i++){
            data[i] = extract.getStatForPlayer(players.get(i));
        }
        return new StatDistribution(data);
    }

    public List<PlayerStat> getRankedNBAPlayers(){
        List<PlayerStatVector> nbaPlayerStatMagnitudes= new ArrayList<>();

        for(PlayerStat p: players){
            generatePlayerVectorMagnitudeMap(p, nbaPlayerStatMagnitudes);
        }
        return rankPlayersUsingMagnitudes(nbaPlayerStatMagnitudes);
    }


    /**
     * Invoke this when all the distribution object for each stat
     * @param playerStat
     * @return
     */
    //Generate for each player
    private void generatePlayerVectorMagnitudeMap(PlayerStat playerStat, List<PlayerStatVector> global_list){
        //FG and FT value already normalized
        // FG%, FT%, 3pts, pts, reb, ast, st, blk, to
        double[] vector = new double[9];
        vector[0] = playerStat.getFG_percent();
        vector[1] = playerStat.getFT_percent();
        vector[2] = threeDistribution.normalize_value(playerStat.getThreePtSeasonAvg());
        vector[3] = pointDistribution.normalize_value(playerStat.getPtSeasonAvg());
        vector[4] = rebDistribution.normalize_value(playerStat.getRebSeasonAvg());
        vector[5] = assistDistribution.normalize_value(playerStat.getAstSeasonAvg());
        vector[6] = stealDistribution.normalize_value(playerStat.getStSeasonAvg());
        vector[7] = blockDistribution.normalize_value(playerStat.getBLKSeasonAvg());
        vector[8] = (1 - toDistribution.normalize_value(playerStat.getTOSeasonAvg()));

        PlayerStatVector player_vector =  new PlayerStatVector(playerStat, vector, amplifierArray);
        global_list.add(player_vector);
    }

    private List<PlayerStat> rankPlayersUsingMagnitudes(List<PlayerStatVector> playerStatVectors){
        //iterate one by one, each iteration is the rank

        //sort by value in map())
        Collections.sort(playerStatVectors);

        //get keys and iterate and assign rank
        int rank = 1;
        List<PlayerStat> rankedPlayers = new ArrayList<>();

        for(PlayerStatVector p: playerStatVectors){
            p.getPlayer().setRank(rank);
            rankedPlayers.add(p.getPlayer());
            rank++;
        }

        return rankedPlayers;

    }

    /*

    public static void main(String[] args){
        HashMap<PlayerStatVector, Double> map = new HashMap<>();

        map.put(new PlayerStatVector(null, null), 3.0);
        map.put(new PlayerStatVector(null, null), 1.0);
        map.put(new PlayerStatVector(null, null), 2.0);

        sortByValue(map);
        return;
    }


     */
}
