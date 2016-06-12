package com.antran.expressfootball.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnTran on 04/03/2016.
 */
public class Leagues {

    public static final int CHAMPION_LEAGUE_ID = 405;

    private static Leagues mInstance;
    private static Context mCtx;
    private List<League> leagues;

    private Leagues(Context context) {
        mCtx = context;
        leagues = getLeagues();
    }

    public static synchronized Leagues getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Leagues(context);
        }
        return mInstance;
    }

    public List<League> getLeagues() {
        if (leagues == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            leagues = new ArrayList<League>();
        }
        return leagues;
    }

    public List<League> getNationalLeagues(){
        List<League> nationalLeague = new ArrayList<League>();
        if (leagues != null){
            for (int i = 0; i < leagues.size(); i++) {
                if (leagues.get(i).getLeagueId() != CHAMPION_LEAGUE_ID) {
                    nationalLeague.add(leagues.get(i));
                }
            }
        }
        return  nationalLeague;
    }

    public List<League> getShowRankingLeagues(){
        List<League> showRankingLeague = new ArrayList<League>();
        if (leagues != null){
            for (int i = 0; i < leagues.size(); i++) {
                if (leagues.get(i).getShowRanking() == 1) {
                    showRankingLeague.add(leagues.get(i));
                }
            }
        }
        return  showRankingLeague;
    }

    public List<League> getShowFavoriteLeagues(){
        List<League> showFavoriteLeague = new ArrayList<League>();
        if (leagues != null){
            for (int i = 0; i < leagues.size(); i++) {
                if (leagues.get(i).getShowFavorite() == 1) {
                    showFavoriteLeague.add(leagues.get(i));
                }
            }
        }
        return  showFavoriteLeague;
    }

    public void importLeagues(JSONArray jsonArray) throws JSONException {
        leagues.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            League league = League.parse(jsonObject);
            leagues.add(league);
        }
    }

    public String getLeagueName(int leagueId) {
        for (int i = 0; i < leagues.size(); i++) {
            if (leagues.get(i).getLeagueId() == leagueId)
                return leagues.get(i).getLeagueName();
        }
        return null;
    }
}
