package com.antran.expressfootball.matchesfragment;

import com.antran.expressfootball.model.League;
import com.antran.expressfootball.model.Team;
import com.parse.ParseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by AnTran on 12/12/2015.
 */
public class MatchInfo {

    private int matchId;
    private League league;
    //    private int leagueId;
    private Date date;
    private String linkVideo;
    private int round;
    private int homeTeamScore;
    private int awayTeamScore;
    private String stadium;
    private Team homeTeam;
    private Team awayTeam;
    //    private int homeTeamId;
//    private int awayTeamId;
    private String thumbnail;
    private String title;
    private String linkType;

    public MatchInfo() {
        matchId = 0;
        league = new League();
        date = new Date();
        linkVideo = "";
        round = 0;
        homeTeamScore = 0;
        awayTeamScore = 0;
        stadium = "";
        homeTeam = new Team();
        awayTeam = new Team();
        thumbnail = "";
        title = "";
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLinkVideo() {
        return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

//    public int getLeagueId() {
//        return leagueId;
//    }
//
//    public void setLeagueId(int leagueId) {
//        this.leagueId = leagueId;
//    }
//
//    public int getHomeTeamId() {
//        return homeTeamId;
//    }
//
//    public void setHomeTeamId(int homeTeamId) {
//        this.homeTeamId = homeTeamId;
//    }
//
//    public int getAwayTeamId() {
//        return awayTeamId;
//    }
//
//    public void setAwayTeamId(int awayTeamId) {
//        this.awayTeamId = awayTeamId;
//    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public static MatchInfo parse(ParseObject object) {
        MatchInfo matchInfo = new MatchInfo();

        matchInfo.setMatchId(object.getInt("match_id"));
        League leagueTmp = new League();
        leagueTmp.setLeagueId(object.getInt("league_id"));
        matchInfo.setLeague(leagueTmp);
        long dateInMillis = object.getLong("date_match");
        matchInfo.setDate(new Date(dateInMillis));
        matchInfo.setLinkVideo(object.getString("link_video"));
        matchInfo.setRound(object.getInt("round"));
        matchInfo.setHomeTeamScore(object.getInt("score1"));
        matchInfo.setAwayTeamScore(object.getInt("score2"));
        Team homeTeamTmp = new Team();
        homeTeamTmp.setTeamName(object.getString("team1"));
        matchInfo.setHomeTeam(homeTeamTmp);
        Team awayTeamTmp = new Team();
        awayTeamTmp.setTeamName(object.getString("team2"));
        matchInfo.setAwayTeam(awayTeamTmp);
        matchInfo.setStadium(object.getString("stadium"));
        matchInfo.setThumbnail(object.getString("thumbnail"));
        matchInfo.setTitle(object.getString("title"));
        matchInfo.setLinkType(object.getString("link_type"));
        return matchInfo;
    }

    public static MatchInfo parse(JSONObject object) throws JSONException {
        MatchInfo matchInfo = new MatchInfo();

        matchInfo.setMatchId(object.getInt("match_id"));
        League leagueTmp = new League();
        JSONObject league = object.getJSONObject("league");
        leagueTmp.setLeagueId(league.getInt("league_id"));
        leagueTmp.setLeagueName(league.getString("name"));
        matchInfo.setLeague(leagueTmp);
        if (object.has("date_match")) {
            long dateInMillis = object.getLong("date_match");
            matchInfo.setDate(new Date(dateInMillis));
        }
        matchInfo.setLinkVideo(object.getString("link_video"));
        if (object.has("round"))
            matchInfo.setRound(object.getInt("round"));
        if (object.has("score1"))
            matchInfo.setHomeTeamScore(object.getInt("score1"));
        if (object.has("score2"))
            matchInfo.setAwayTeamScore(object.getInt("score2"));
        Team homeTeamTmp = new Team();
        if (object.has("team1")) {
            JSONObject team1 = object.getJSONObject("team1");
            homeTeamTmp.setTeamId(team1.getInt("team_id"));
            homeTeamTmp.setTeamName(team1.getString("name"));
            homeTeamTmp.setCrestUrl(team1.getString("crest_url"));
        }
        matchInfo.setHomeTeam(homeTeamTmp);
        Team awayTeamTmp = new Team();
        if (object.has("team2")) {
            JSONObject team2 = object.getJSONObject("team2");
            homeTeamTmp.setTeamId(team2.getInt("team_id"));
            homeTeamTmp.setTeamName(team2.getString("name"));
            homeTeamTmp.setCrestUrl(team2.getString("crest_url"));
        }
        matchInfo.setAwayTeam(awayTeamTmp);
        if (object.has("stadium"))
            matchInfo.setStadium(object.getString("stadium"));
        if (object.has("thumbnail"))
            matchInfo.setThumbnail(object.getString("thumbnail"));
        if (object.has("title"))
            matchInfo.setTitle(object.getString("title"));
        if (object.has("link_type"))
            matchInfo.setLinkType(object.getString("link_type"));
        return matchInfo;
    }
}
