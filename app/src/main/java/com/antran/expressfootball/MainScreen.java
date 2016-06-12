package com.antran.expressfootball;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.favoritefragment.FavoriteFragment;
import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;
import com.antran.expressfootball.main.MainController;
import com.antran.expressfootball.main.MainControllerListener;
import com.antran.expressfootball.main.MainView;
import com.antran.expressfootball.main.menu.MenuController;
import com.antran.expressfootball.main.menu.MenuControllerListener;
import com.antran.expressfootball.main.menu.MenuView;
import com.antran.expressfootball.matchesfragment.FavoriteTeamMatchFragment;
import com.antran.expressfootball.matchesfragment.LastedHighlightFragment;
import com.antran.expressfootball.matchesfragment.LeagueFragment;
import com.antran.expressfootball.rankingfragment.RankingFragment;
import com.antran.expressfootball.util.Key;

/**
 * Created by AnTran on 27/11/2015.
 */
public class MainScreen extends FragmentActivity implements LoadingControllerListener, MainControllerListener, MenuControllerListener {

    //Defining Variables
    private Activity activity;
    private Context context;
    //
    private LoadingController loadingController;
    private MainController mainController;
    private MenuController menuController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sceen_view);
        activity = this;
        context = this;

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        MainView mainView = new MainView(this, (DrawerLayout) findViewById(R.id.drawer));
        mainController = new MainController(context, mainView, this);
        mainView.setListener(mainController);

        MenuView menuView = new MenuView((NavigationView) findViewById(R.id.navigation_view));
        menuController = new MenuController(context, menuView, this);
        menuView.setListener(menuController);
        menuController.loadLeagueMenuItems();

        if (savedInstanceState == null)
            onClickLastedHighLight();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (menuController.isShown())
            mainController.closeDrawers();
        else
            super.onBackPressed();
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }

    @Override
    public void closeDrawers() {
        mainController.closeDrawers();
    }

    @Override
    public void onClickLastedHighLight() {
        mainController.setTitle(getString(R.string.menu_latest_highlight));
        LastedHighlightFragment fragment = new LastedHighlightFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickMyFavorite() {
        mainController.setTitle(getString(R.string.menu_my_favorite));
        String teamJson = CachingManager.getInstance(context).getStringCached(CachingContant.FAVORITE_TEAM, "");
        if (teamJson == null || teamJson.compareTo("") == 0) {
            FavoriteFragment fragment = new FavoriteFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        } else {
            FavoriteTeamMatchFragment fragment = new FavoriteTeamMatchFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.commit();
        }
    }

    public void openFavoriteTeamsSelect() {
        mainController.setTitle(getString(R.string.select_your_team));
        FavoriteFragment fragment = new FavoriteFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClickRankingTable() {
        mainController.setTitle(getString(R.string.menu_ranking_table));
        RankingFragment fragment = new RankingFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onLoading() {
        loadingController.showLoading();
    }

    @Override
    public void onLoaded() {
        loadingController.hideLoading();
    }

    @Override
    public MenuItem.OnMenuItemClickListener onSubMenuItemClick(final int leagueId, final String leagueName) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                item.setChecked(true);
                mainController.setTitle(leagueName);
                LeagueFragment fragment = new LeagueFragment();
                Bundle arguments = new Bundle();
                arguments.putInt(Key.LEAGUE_ID, leagueId);
                fragment.setArguments(arguments);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame, fragment);
                fragmentTransaction.commit();
                return false;
            }
        };
    }

    public void reset() {
        menuController.loadLeagueMenuItems();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mainController.syncState();
    }
}
