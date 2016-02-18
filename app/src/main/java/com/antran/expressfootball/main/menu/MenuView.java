package com.antran.expressfootball.main.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import com.antran.expressfootball.R;
import com.antran.expressfootball.callback.ImageLoadCallback;
import com.antran.expressfootball.matchesfragment.LastedHighlightFragment;
import com.antran.expressfootball.util.ImageLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;

/**
 * Created by AnTran on 13/12/2015.
 */
public class MenuView {

    private NavigationView navigationView;
    private Menu menu;
    private SubMenu leagueItems;
    private Picasso picasso;

    public MenuView(NavigationView navigationView) {
        this.navigationView = navigationView;
        this.navigationView.setItemIconTintList(null);
        menu = navigationView.getMenu();
        leagueItems = menu.addSubMenu(R.id.default_group, Menu.NONE, Menu.NONE, "League");
        picasso = Picasso.with(navigationView.getContext().getApplicationContext());
        picasso.setIndicatorsEnabled(false);
        picasso.setLoggingEnabled(true);
    }

    public void setListener(final MenuViewListener ltn) {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                ltn.onMenuItemSelected(menuItem);
                return true;
            }
        });
    }

    public void addLeagueMenuItem(int itemId, String title, String icon, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        leagueItems.add(R.id.default_group, itemId, Menu.NONE, title);

        final MenuItem menuItem = leagueItems.findItem(itemId);
        menuItem.setTitle(title);
        menuItem.setCheckable(true);
        if (icon != null && icon.compareTo("") != 0){
            ImageLoader imageLoader = new ImageLoader(navigationView.getResources(), icon, new ImageLoadCallback() {
                @Override
                public void onPreLoad() {
                }

                @Override
                public void onLoadSuccessfully(Drawable drawable) {
                    menuItem.setIcon(drawable);
                }

                @Override
                public void onLoadFailure() {
                    Toast.makeText(navigationView.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });
            imageLoader.execute();
        }
//        picasso.load(icon).placeholder(R.drawable.ic_ball).into(new Target() {
//                @Override
//                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//                    menuItem.setIcon(new BitmapDrawable(navigationView.getResources(), bitmap));
//                }
//
//                @Override
//                public void onBitmapFailed(Drawable errorDrawable) {
//                    Toast.makeText(navigationView.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onPrepareLoad(Drawable placeHolderDrawable) {
//                    menuItem.setIcon(placeHolderDrawable);
//                }
//            });

        menuItem.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public void clearLeagueMenuItem() {
        leagueItems.clear();
    }
}
