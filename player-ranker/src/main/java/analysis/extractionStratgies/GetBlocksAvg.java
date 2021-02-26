package analysis.extractionStratgies;

import model.PlayerStat;

public class GetBlocksAvg implements  StatExtractionStategy{
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getBLKSeasonAvg();
    }
}
