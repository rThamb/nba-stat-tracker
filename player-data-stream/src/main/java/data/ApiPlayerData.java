package data;

import logger.MyLogger;
import model.PlayerStat;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.slf4j.Logger;
import util.ConfigLoader;
import util.MyHttpClient;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ApiPlayerData implements PlayerRepo{

    private static final String configPath = "config/config.properties";
    private static Logger log = MyLogger.getLogger();
    private MyHttpClient http;
    private String endpoint;
    private String[] teamKeys;

    public ApiPlayerData() throws IOException {
        Properties prop = ConfigLoader.load(configPath);
        this.http = new MyHttpClient();
        this.endpoint = prop.getProperty("api.endpoint.url");
        this.teamKeys = prop.getProperty("api.team.keys").split(",");
    }
    @Override
    public List<PlayerStat> getPlayers() {
        List<PlayerStat> data = new ArrayList();

        for(int i = 0; i < teamKeys.length; i++){
            String teamKey = teamKeys[i];
            log.info(String.format("Loading Player data by team (%s): %s of %s - player count %s",
                    teamKey, i + 1 + "", teamKeys.length + "", data.size() + ""));

            String url = endpoint.replace("{TEAM_ID}", teamKey);
            String response = http.get(url);
            JSONArray playersJsonCurrentTeam = new JSONArray(response);
            parsePlayerData(playersJsonCurrentTeam, data);

        }

        return data;
    }

    private void parsePlayerData(JSONArray playerJson, List<PlayerStat> players){

        for(int i =0; i < playerJson.length();i++){
            try {
                players.add(getPlayer(playerJson.getJSONObject(i)));
            } catch (Exception e) {
                log.info("Error parsing a player: SKIPPED");
            }
        }

    }

    private PlayerStat getPlayer(JSONObject playerJson) throws Exception{

        PlayerStat p = new PlayerStat();
        double games = playerJson.getDouble("Games");
        double fg_per = playerJson.getDouble("FieldGoalsPercentage") / 100.00;
        double ft_per = playerJson.getDouble("FreeThrowsPercentage") / 100.00;
        double threePts = playerJson.getDouble("ThreePointersMade");
        double threePts_per = round(threePts / games);

        double pts = playerJson.getDouble("Points");
        double pts_avg = round(pts / games);

        double ast = playerJson.getDouble("Assists");
        double ast_avg = round(ast / games);

        double reb = playerJson.getDouble("Rebounds");
        double reb_avg = round(reb / games);

        double stl = playerJson.getDouble("Steals");
        double stl_avg = round(stl / games);

        double blk = playerJson.getDouble("BlockedShots");
        double blk_avg = round(blk / games);

        double to = playerJson.getDouble("Turnovers");
        double to_avg = round(to / games);

        String[] name = playerJson.getString("Name").split(" ");
        String fname = name[0];
        String lname = name[1];
        String pos = playerJson.getString("Position");
        String team = playerJson.getString("Team");

        p.setPosition(pos);
        p.setFname(fname);
        p.setLname(lname);
        p.setTeam(team);
        p.setAstMade(ast);
        p.setAstSeasonAvg(ast_avg);
        p.setRebMade(reb);
        p.setRebSeasonAvg(reb_avg);
        p.setStMade(stl);
        p.setStSeasonAvg(stl_avg);
        p.setBLKMade(blk);
        p.setBLKSeasonAvg(blk_avg);
        p.setTOMade(to);
        p.setTOSeasonAvg(to_avg);
        p.setFG_percent(fg_per);
        p.setFT_percent(ft_per);
        p.setThreePtsMade(threePts);
        p.setThreePtSeasonAvg(threePts_per);
        p.setPtsMade(pts);
        p.setPtSeasonAvg(pts_avg);

        return p;
    }

    private double round(double num){
        return (double)Math.round(num * 10000000d) / 10000000d;
    }


}
