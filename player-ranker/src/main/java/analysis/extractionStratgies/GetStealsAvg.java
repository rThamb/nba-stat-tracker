package analysis.extractionStratgies;

import model.PlayerStat;

public class GetStealsAvg implements StatExtractionStategy {
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getStSeasonAvg();
    }
}
