package com.antran.expressfootball.service;

import com.parse.FindCallback;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AnTran on 12/12/2015.
 */
public class MatchDataHelper {

    private static final String ClassMatch = "match";
    private static final String ClassLeague = "leagues";

    public static void getMatchesFromLeague(int leagueId, final FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> queryMatch = ParseQuery.getQuery(ClassMatch);
//        ParseQuery<ParseObject> queryLeague = ParseQuery.getQuery(ClassLeague);
//        queryLeague.whereEqualTo("league_id", leagueId);
        queryMatch.whereEqualTo("league_id", leagueId);
//        queryMatch.include("league");
//        queryMatch.include("team1");
//        queryMatch.include("team2");
        queryMatch.orderByDescending("dateMatch");
        queryMatch.findInBackground(callback);
    }

    public static void getAllMatches(final FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> queryMatch = ParseQuery.getQuery(ClassMatch);
//        queryMatch.include("league");
//        queryMatch.include("team1");
//        queryMatch.include("team2");
        queryMatch.orderByDescending("dateMatch");
        queryMatch.findInBackground(callback);
    }

    public static void getMatchFromId(final int id, final FunctionCallback<JSONObject> callback) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("match_id", id);
        ParseCloud.callFunctionInBackground("GetMatchById_3", map, callback);
    }

    public static void getLimitMatches(int limit, final FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> queryMatch = ParseQuery.getQuery(ClassMatch);
//        queryMatch.include("league");
//        queryMatch.include("team1");
//        queryMatch.include("team2");
        queryMatch.orderByDescending("dateMatch");
        queryMatch.setLimit(limit);
        queryMatch.findInBackground(callback);
    }

    public static void getLimitMatchesFromLeague(int leagueId, int limit, final FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> queryMatch = ParseQuery.getQuery(ClassMatch);
//        ParseQuery<ParseObject> queryLeague = ParseQuery.getQuery(ClassLeague);
//        queryLeague.whereEqualTo("league_id", leagueId);
        queryMatch.whereEqualTo("league_id", leagueId);
//        queryMatch.include("league");
//        queryMatch.include("team1");
//        queryMatch.include("team2");
        queryMatch.orderByDescending("dateMatch");
        queryMatch.setLimit(limit);
        queryMatch.findInBackground(callback);
    }
}
