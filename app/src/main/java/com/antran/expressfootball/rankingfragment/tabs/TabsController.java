package com.antran.expressfootball.rankingfragment.tabs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.antran.expressfootball.model.League;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.rankingfragment.tablefragment.TableFragment;
import com.antran.expressfootball.util.Key;

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
    private HashMap<Integer, Integer> idMapping;

    public TabsController(Context context, FragmentManager fragmentManager, TabsView tabsView, TabsControllerListener ltn) {
        super(fragmentManager);
        this.context = context;
        this.tabsView = tabsView;
        this.listener = ltn;
        this.leagues = Leagues.getInstance(context).getLeagues();
        this.idMapping = new HashMap<>();

    }

    public void firstLoad() {
        tabsView.clearTabs();
        int leaguesSize = leagues.size();
        int index = 0;
        for (int i = 0; i < leaguesSize; i++) {
            if (leagues.get(i).getLeagueId() != 405) {
                tabsView.addTab(leagues.get(i).getLogo());
                idMapping.put(index, leagues.get(i).getLeagueId());
                index++;
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        TableFragment fragment = new TableFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(Key.LEAGUE_ID, idMapping.get(position));
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getCount() {
        return leagues.size() - 1;
    }
}
