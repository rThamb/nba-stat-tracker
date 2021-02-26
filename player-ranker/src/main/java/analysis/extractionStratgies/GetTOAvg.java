package analysis.extractionStratgies;

import model.PlayerStat;

public class GetTOAvg implements StatExtractionStategy {
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getTOSeasonAvg();
    }
}
