package com.antran.expressfootball.model;

import com.parse.ParseException;
import com.parse.ParseObject;

/**
 * Created by AnTran on 12/12/2015.
 */
public class Team {

    private int teamId;
    private String teamName;
    private String logo;
    private League league;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public static Team parse(ParseObject object) {
        Team team = new Team();
        team.setTeamId(object.getInt("team_id"));
        team.setTeamName(object.getString("name"));
        team.setCrestUrl(object.getString("crestUrl"));
//        team.setLogo(object.getParseFile("thumbnail").getUrl());
//        try {
//            team.setLeagueId(League.parse(object.getParseObject("league").fetchIfNeeded()));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return team;
    }
}
