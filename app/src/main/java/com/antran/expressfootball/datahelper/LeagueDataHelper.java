package com.antran.expressfootball.datahelper;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by AnTran on 13/12/2015.
 */
public class LeagueDataHelper {
    private static final String Class = "league";

    public static void getLeagues(final FindCallback<ParseObject> callback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Class);
        query.findInBackground(callback);
    }

    public static void getLeagueFromId(int id, final FindCallback<ParseObject> callback){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Class);
        query.whereEqualTo("league_id", id);
        query.findInBackground(callback);
    }
}
