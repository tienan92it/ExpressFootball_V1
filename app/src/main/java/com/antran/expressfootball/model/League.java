package com.antran.expressfootball.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AnTran on 12/12/2015.
 */
public class League {

    private int leagueId;
    private String leagueName;
    private int priority;
    private String logo;
    private int status;

    public int getShowRanking() {
        return showRanking;
    }

    public void setShowRanking(int showRanking) {
        this.showRanking = showRanking;
    }

    public int getShowFavorite() {
        return showFavorite;
    }

    public void setShowFavorite(int showFavorite) {
        this.showFavorite = showFavorite;
    }

    private int showRanking;
    private int showFavorite;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static League parse(ParseObject object) {
        League league = new League();
        league.setLeagueId(object.getInt("league_id"));
        league.setLeagueName(object.getString("name"));
        league.setPriority(object.getInt("order"));
        league.setStatus(object.getInt("status"));
        league.setLogo(object.getString("thumbnail"));
        league.setShowRanking(object.getInt("show_ranking"));
        league.setShowFavorite(object.getInt("show_favorite"));
        return league;
    }

    public static League parse(JSONObject object) throws JSONException {
        League league = new League();
        league.setLeagueId(object.getInt("league_id"));
        league.setLeagueName(object.getString("name"));
        league.setPriority(object.getInt("order"));
        league.setStatus(object.getInt("status"));
        league.setLogo(object.getString("thumbnail"));
        league.setShowRanking(object.getInt("show_ranking"));
        league.setShowFavorite(object.getInt("show_favorite"));
        return league;
    }
}
