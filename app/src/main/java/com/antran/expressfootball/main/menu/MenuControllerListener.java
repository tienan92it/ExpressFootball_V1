package com.antran.expressfootball.main.menu;

import android.view.MenuItem;

/**
 * Created by AnTran on 13/12/2015.
 */
public interface MenuControllerListener {
    public void closeDrawers();

    public void onClickLastedHighLight();

    public void onClickMyFavorite();

    public void onClickRankingTable();

    public void onLoading();

    public void onLoaded();

    public MenuItem.OnMenuItemClickListener onSubMenuItemClick(int leagueId, String leagueName);
}
