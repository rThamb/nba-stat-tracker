package analysis.extractionStratgies;

import model.PlayerStat;

public class GetRebAvg implements StatExtractionStategy {
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getRebSeasonAvg();
    }
}
