package com.antran.expressfootball.main.menu;

import android.content.Context;
import android.view.MenuItem;

import com.antran.expressfootball.R;
import com.antran.expressfootball.model.League;
import com.antran.expressfootball.service.LeagueDataHelper;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

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

    public MenuController(Context context, MenuView menuView, MenuControllerListener ltn) {
        this.context = context;
        this.menuView = menuView;
        this.listener = ltn;
        menuItems = new ArrayList<League>();
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
//            case R.id.la_liga:
//                listener.onClickLigaBBVA();
//                break;
            case R.id.ranking_table:
                listener.onClickRankingTable();
                break;
            default:
                break;

        }
    }

    public void loadLeagueMenuItems() {
        listener.onLoading();
        menuItems.clear();
        menuView.clearLeagueMenuItem();
        LeagueDataHelper.getLeagues(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects.size() > 0) {
                    for (ParseObject item : objects) {
                        League league = League.parse(item);
                        if (league.getStatus() == 1) {
                            menuItems.add(league);
                            menuView.addLeagueMenuItem(league.getPriority(), league.getLeagueName(), league.getLogo(),
                                    listener.onSubMenuItemClick(league.getLeagueId(), league.getLeagueName()));
                        }
                    }
                } else {
                    e.printStackTrace();
                }
                listener.onLoaded();
            }
        });

    }
}
