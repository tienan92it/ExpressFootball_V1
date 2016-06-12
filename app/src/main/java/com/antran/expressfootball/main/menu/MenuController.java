package com.antran.expressfootball.main.menu;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.antran.expressfootball.R;
import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.model.League;
import com.antran.expressfootball.datahelper.LeagueDataHelper;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnTran on 13/12/2015.
 */
public class MenuController implements MenuViewListener {

    private Context context;
    private List<League> menuItems;
    private MenuView menuView;
    private MenuControllerListener listener;
    private List<League> leagues;

    public MenuController(Context context, MenuView menuView, MenuControllerListener ltn) {
        this.context = context;
        this.menuView = menuView;
        this.listener = ltn;
        menuItems = new ArrayList<League>();
        this.leagues = Leagues.getInstance(context).getLeagues();
    }

    @Override
    public void onMenuItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);

        //Closing drawer on item click
        listener.closeDrawers();

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {


            //Replacing the main content with ContentFragment Which is our Inbox View;
            case R.id.lasted_highlight:
                listener.onClickLastedHighLight();
                break;
            case R.id.my_favorite:
                listener.onClickMyFavorite();
                break;
            case R.id.ranking_table:
                listener.onClickRankingTable();
                break;
            default:
                break;

        }
    }

    public void loadLeagueMenuItems() {
        menuItems.clear();
        menuView.clearLeagueMenuItem();
        int leaguesSize = leagues.size();
        for (int i = 0; i < leaguesSize; i++) {
            League league = leagues.get(i);
            if (league.getStatus() == 1) {
                menuItems.add(league);
                menuView.addLeagueMenuItem(league.getPriority(), league.getLeagueName(), league.getLogo(),
                        listener.onSubMenuItemClick(league.getLeagueId(), league.getLeagueName()));
            }
        }
    }

    public boolean isShown(){
        return menuView.isShown();
    }
}
