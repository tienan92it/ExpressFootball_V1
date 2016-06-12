package com.antran.expressfootball.rankingfragment.tabs;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.antran.expressfootball.R;
import com.antran.expressfootball.model.League;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.networkrequest.MySingleton;
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
    private ImageLoader imageLoader;

    public TabsController(Context context, FragmentManager fragmentManager, TabsView tabsView, TabsControllerListener ltn) {
        super(fragmentManager);
        this.context = context;
        this.tabsView = tabsView;
        this.listener = ltn;
        this.leagues = Leagues.getInstance(context).getNationalLeagues();
        imageLoader = MySingleton.getInstance(context).getImageLoader();
    }

    public void firstLoad() {
        tabsView.clearTabs();
        this.leagues = Leagues.getInstance(context).getShowRankingLeagues();
        notifyDataSetChanged();
        if (leagues.size() > 0) {
            int leaguesSize = leagues.size();
            for (int i = 0; i < leaguesSize; i++) {
                tabsView.addTab(leagues.get(i).getLogo(), i);
            }
        } else {
            listener.lostConnection();
        }
    }

    @Override
    public Fragment getItem(int position) {
        TableFragment fragment = new TableFragment();
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
