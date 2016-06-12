package com.antran.expressfootball.rankingfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.antran.expressfootball.MainScreen;
import com.antran.expressfootball.R;
import com.antran.expressfootball.rankingfragment.tabs.TabsController;
import com.antran.expressfootball.rankingfragment.tabs.TabsControllerListener;
import com.antran.expressfootball.rankingfragment.tabs.TabsView;
import com.antran.expressfootball.retryfragment.RetryController;
import com.antran.expressfootball.retryfragment.RetryControllerListener;
import com.antran.expressfootball.retryfragment.RetryView;

/**
 * Created by AnTran on 05/03/2016.
 */
public class RankingFragment extends Fragment implements RetryControllerListener, TabsControllerListener{

    private Context context;
    private MainScreen activity;

    private RetryController retryController;
    private TabsController tabsController;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        activity = (MainScreen) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.ranking_fragment, container, false);

        RetryView retryView = new RetryView((RelativeLayout) rootview.findViewById(R.id.retry_content));
        retryController = new RetryController(context, retryView, this);
        retryView.setListener(retryController);

        viewPager = (ViewPager) rootview.findViewById(R.id.viewpager);

        TabsView tabsView = new TabsView((TabLayout) rootview.findViewById(R.id.tabs));
        tabsController = new TabsController(context, getChildFragmentManager(), tabsView, this);
        tabsView.setListener(tabsController);

        viewPager.setAdapter(tabsController);
        tabsView.setViewPager(viewPager);

        tabsController.firstLoad();
        return rootview;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRetry() {
        retryController.hide();
        viewPager.setVisibility(View.VISIBLE);
        activity.reset();
        tabsController.firstLoad();
    }

    @Override
    public void lostConnection() {
        retryController.show();
        viewPager.setVisibility(View.GONE);
    }
}
