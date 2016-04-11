package com.antran.expressfootball.model;

import com.parse.ParseException;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AnTran on 12/12/2015.
 */
public class Team {

    private int teamId;
    private String teamName;
    private String logo;
    private int leagueId;
    private String crestUrl;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public static Team parse(JSONObject object) throws JSONException {
        Team team = new Team();
        team.setTeamId(object.getInt("team_id"));
        team.setTeamName(object.getString("short_name"));
        team.setCrestUrl(object.getString("crest_url"));
        team.setLeagueId(object.getInt("league_id"));
//        team.setLogo(object.getParseFile("thumbnail").getUrl());
//        try {
//            team.setLeagueId(League.parse(object.getParseObject("league").fetchIfNeeded()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return team;
    }
}
