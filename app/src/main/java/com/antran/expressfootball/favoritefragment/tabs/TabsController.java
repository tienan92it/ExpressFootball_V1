package com.antran.expressfootball.favoritefragment.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.favoritefragment.teamsfragment.TeamsFragment;
import com.antran.expressfootball.model.League;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.util.Key;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

/**
 * Created by AnTran on 05/03/2016.
 */
public class TabsController extends FragmentPagerAdapter implements TabsViewListener {

    private Context context;
    private TabsView tabsView;
    private TabsControllerListener listener;
    private List<League> leagues;
    private Team currentFavorite;

    public TabsController(Context context, FragmentManager fragmentManager, TabsView tabsView, TabsControllerListener ltn) {
        super(fragmentManager);
        this.context = context;
        this.tabsView = tabsView;
        this.listener = ltn;
        this.leagues = Leagues.getInstance(context).getLeagues();
        currentFavorite = new Team();
        String teamJson = CachingManager.getInstance(context).getStringCached(CachingContant.FAVORITE_TEAM, "");
        if (teamJson != null && teamJson.compareTo("") != 0){
            Gson gson = new Gson();
            currentFavorite = gson.fromJson(teamJson, Team.class);
        }
    }

    public void firstLoad() {
        tabsView.clearTabs();
        this.leagues = Leagues.getInstance(context).getShowFavoriteLeagues();
        notifyDataSetChanged();
        if (leagues.size() > 0) {
            int leaguesSize = leagues.size();
            for (int i = 0; i < leaguesSize; i++) {
                tabsView.addTab(leagues.get(i).getLogo(), i);
            }
            if (currentFavorite != null && currentFavorite.getTeamId() != -1){
                for (int i = 0; i < leaguesSize; i++) {
                    if (leagues.get(i).getLeagueId() == currentFavorite.getLeagueId()){
                        tabsView.selectTab(i);
                    }
                }
            }
        } else {
            listener.lostConnection();
        }
    }

    @Override
    public Fragment getItem(int position) {
        TeamsFragment fragment = new TeamsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(Key.LEAGUE_ID, leagues.get(position).getLeagueId());
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getCount() {
        return leagues.size();
    }
}
