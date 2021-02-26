package analysis.extractionStratgies;

import model.PlayerStat;

public class GetThreeAvg implements StatExtractionStategy {
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getThreePtSeasonAvg();
    }
}
