package com.antran.expressfootball.service;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Created by AnTran on 26/01/2016.
 */
public class TeamDataHelper {
    private static final String Class = "team";

    public static void getTeamFromId(int id, final FindCallback<ParseObject> callback){
        ParseQuery<ParseObject> query = ParseQuery.getQuery(Class);
        query.whereEqualTo("team_id", id);
        query.findInBackground(callback);
    }
}
