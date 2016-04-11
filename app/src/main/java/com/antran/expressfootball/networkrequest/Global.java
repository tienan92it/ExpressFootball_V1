package com.antran.expressfootball.networkrequest;

/**
 * Created by AnTran on 05/03/2016.
 */
public class Global {

    public static final String DOMAIN = "http://myapp-expressapp.rhcloud.com";
    public static final String GET_MATCHES = DOMAIN + "/matches/";
    public static final String GET_MATCHES_BY_LEAGUEID = DOMAIN + "/matchesbyleagueid/";
    public static final String GET_LEAGUES = DOMAIN + "/leagues/";
    public static final String GET_TEAMS = DOMAIN + "/teams/";
    public static final String GET_RANKING = DOMAIN + "/ranking/";
    public static final String GET_TEAMS_BY_LEAGUEID = DOMAIN + "/teamsbyleagueid/";
    public static final String GET_MATCHES_BY_TEAMNAME = DOMAIN + "/matchesbyteamname";
    public static final String GET_TEAM_BY_ID = DOMAIN + "/teams/";

    /**
     * @param page
     * @param limit
     * @param orderBy field to order
     * @param sort asc/desc
     * @return string URL
     */
    public static String getURLMatchesWith(int page, int limit, String orderBy, String sort){
        return GET_MATCHES + "?page=" + page + "&&limit=" + limit + "&&orderBy=" + orderBy + "&&asc=" + sort;
    }

    /**
     * @param leagueId
     * @param page
     * @param limit
     * @param orderBy
     * @param sort
     * @return
     */
    public static String getURLMatchesByLeagueIdWith(int leagueId, int page, int limit, String orderBy, String sort){
        return GET_MATCHES_BY_LEAGUEID + leagueId + "?page=" + page + "&&limit=" + limit + "&&orderBy=" + orderBy + "&&asc=" + sort;
    }

    /**
     * @param page
     * @param limit
     * @param orderBy field to order
     * @param sort asc/desc
     * @return string URL
     */
    public static String getURLLeaguesWith(int page, int limit, String orderBy, String sort){
        return GET_LEAGUES + "?page=" + page + "&&limit=" + limit + "&&orderBy=" + orderBy + "&&asc=" + sort;
    }

    public static String getURLRanking(int leagueId){
        return GET_RANKING + leagueId + "?page=1&&limit=30&&orderBy=position&&asc=asc";
    }

    public static String getURLTeamsByLeagueId(int leagueId){
        return GET_TEAMS_BY_LEAGUEID + leagueId + "?page=1&&limit=30";
    }

    public static String getURLMatchesByTeamNameWith() {
        return GET_MATCHES_BY_TEAMNAME;
    }

    public static String getURLTeamById(int id){
        return GET_TEAM_BY_ID + id;
    }
}
