package analysis.extractionStratgies;

import model.PlayerStat;

public class GetAssistsAvg implements StatExtractionStategy {
    @Override
    public double getStatForPlayer(PlayerStat player) {
        return player.getAstSeasonAvg();
    }
}
