package analysis.extractionStratgies;

import model.PlayerStat;

public class GetPointsAvg implements StatExtractionStategy{
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getPtSeasonAvg();
    }
}
