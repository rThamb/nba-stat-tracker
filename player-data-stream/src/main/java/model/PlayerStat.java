package model;

import org.json.JSONObject;

public class PlayerStat implements Jsonify{

    private String fname;
    private String lname;
    private String team;

    private double FG_percent;
    private double FT_percent;

    private double threePtsMade;
    private double threePtSeasonAvg;

    private double PtsMade;
    private double PtSeasonAvg;

    private double AstMade;
    private double AstSeasonAvg;

    private double RebMade;
    private double RebSeasonAvg;

    private double StMade;
    private double StSeasonAvg;

    private double BLKMade;
    private double BLKSeasonAvg;

    private double TOMade;
    private double TOSeasonAvg;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public double getFG_percent() {
        return FG_percent;
    }

    public void setFG_percent(double FG_percent) {
        this.FG_percent = FG_percent;
    }

    public double getFT_percent() {
        return FT_percent;
    }

    public void setFT_percent(double FT_percent) {
        this.FT_percent = FT_percent;
    }

    public double getThreePtsMade() {
        return threePtsMade;
    }

    public void setThreePtsMade(double threePtsMade) {
        this.threePtsMade = threePtsMade;
    }

    public double getThreePtSeasonAvg() {
        return threePtSeasonAvg;
    }

    public void setThreePtSeasonAvg(double threePtSeasonAvg) {
        this.threePtSeasonAvg = threePtSeasonAvg;
    }

    public double getPtsMade() {
        return PtsMade;
    }

    public void setPtsMade(double ptsMade) {
        PtsMade = ptsMade;
    }

    public double getPtSeasonAvg() {
        return PtSeasonAvg;
    }

    public void setPtSeasonAvg(double ptSeasonAvg) {
        PtSeasonAvg = ptSeasonAvg;
    }

    public double getAstMade() {
        return AstMade;
    }

    public void setAstMade(double astMade) {
        AstMade = astMade;
    }

    public double getAstSeasonAvg() {
        return AstSeasonAvg;
    }

    public void setAstSeasonAvg(double astSeasonAvg) {
        AstSeasonAvg = astSeasonAvg;
    }

    public double getRebMade() {
        return RebMade;
    }

    public void setRebMade(double rebMade) {
        RebMade = rebMade;
    }

    public double getRebSeasonAvg() {
        return RebSeasonAvg;
    }

    public void setRebSeasonAvg(double rebSeasonAvg) {
        RebSeasonAvg = rebSeasonAvg;
    }

    public double getStMade() {
        return StMade;
    }

    public void setStMade(double stMade) {
        StMade = stMade;
    }

    public double getStSeasonAvg() {
        return StSeasonAvg;
    }

    public void setStSeasonAvg(double stSeasonAvg) {
        StSeasonAvg = stSeasonAvg;
    }

    public double getBLKMade() {
        return BLKMade;
    }

    public void setBLKMade(double BLKMade) {
        this.BLKMade = BLKMade;
    }

    public double getBLKSeasonAvg() {
        return BLKSeasonAvg;
    }

    public void setBLKSeasonAvg(double BLKSeasonAvg) {
        this.BLKSeasonAvg = BLKSeasonAvg;
    }

    public double getTOMade() {
        return TOMade;
    }

    public void setTOMade(double TOMade) {
        this.TOMade = TOMade;
    }

    public double getTOSeasonAvg() {
        return TOSeasonAvg;
    }

    public void setTOSeasonAvg(double TOSeasonAvg) {
        this.TOSeasonAvg = TOSeasonAvg;
    }

    @Override
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("fname", fname);
        obj.put("lname", lname);
        obj.put("team", team);
        obj.put("fg_per", FG_percent);
        obj.put("ft_per", FT_percent);
        obj.put("3pts", threePtsMade);
        obj.put("3pts_avg", threePtSeasonAvg);
        obj.put("pts", PtsMade);
        obj.put("pts_avg", PtSeasonAvg);
        obj.put("ast", AstMade);
        obj.put("ast_avg", AstSeasonAvg);
        obj.put("reb", RebMade);
        obj.put("reb_avg", RebSeasonAvg);
        obj.put("st", StMade);
        obj.put("st_avg", StSeasonAvg);
        obj.put("blk", BLKMade);
        obj.put("blk_avg", BLKSeasonAvg);
        obj.put("to", TOMade);
        obj.put("to_avg", TOSeasonAvg);
        return obj;
    }
}
