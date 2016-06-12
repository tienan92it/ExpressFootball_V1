package com.antran.expressfootball.matchesfragment.matches;

/**
 * Created by AnTran on 05/12/2015.
 */
public interface MatchesControllerListener {
    void onLoading();

    void onLoaded();

    void lostConnection();
}
