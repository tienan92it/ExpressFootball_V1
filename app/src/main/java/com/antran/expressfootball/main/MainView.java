package com.antran.expressfootball.main;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.antran.expressfootball.R;
import com.antran.expressfootball.util.FontManager;

/**
 * Created by AnTran on 13/12/2015.
 */
public class MainView {

    private DrawerLayout drawerLayout;

    private Toolbar toolbar;

    private String titleFont;

    public MainView(Activity activity, DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) drawerLayout.findViewById(R.id.toolbar);
        titleFont = drawerLayout.getContext().getString(R.string.font_roboto_light);

        String title = drawerLayout.getContext().getString(R.string.app_name);
        toolbar.setTitle(FontManager.transferTextToFont(drawerLayout.getContext(), title, titleFont));
        toolbar.setTitleTextColor(Color.WHITE);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(activity, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    public void setListener(MainViewListener ltn) {

    }

    public void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    public void setTitle(String title) {
        toolbar.setTitle(FontManager.transferTextToFont(drawerLayout.getContext(), title, titleFont));
    }
}
