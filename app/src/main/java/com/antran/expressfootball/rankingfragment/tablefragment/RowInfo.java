package com.antran.expressfootball.rankingfragment.tablefragment;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AnTran on 06/03/2016.
 */
public class RowInfo {

    private int position;
    private String logo;
    private String clubName;
    private int playedGame;
    private int goalDifference;
    private int points;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public int getPlayedGame() {
        return playedGame;
    }

    public void setPlayedGame(int playedGame) {
        this.playedGame = playedGame;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public static RowInfo parse(JSONObject jsonObject) throws JSONException {
        RowInfo rowInfo = new RowInfo();

        rowInfo.setPosition(jsonObject.getInt("position"));
        rowInfo.setLogo(jsonObject.getString("crestURI"));
        rowInfo.setClubName(jsonObject.getString("teamName"));
        rowInfo.setPlayedGame(jsonObject.getInt("playedGames"));
        rowInfo.setGoalDifference(jsonObject.getInt("goalDifference"));
        rowInfo.setPoints(jsonObject.getInt("points"));

        return rowInfo;
    }
}
