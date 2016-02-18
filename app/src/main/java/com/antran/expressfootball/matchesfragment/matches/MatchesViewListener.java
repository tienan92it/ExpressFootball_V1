package com.antran.expressfootball.matchesfragment.matches;

/**
 * Created by AnTran on 05/12/2015.
 */
public interface MatchesViewListener {
    public void onRefreshListener();

    public void loadMore(int current_page);
}
