package com.antran.expressfootball.main.menu;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.antran.expressfootball.R;
import com.antran.expressfootball.callback.ImageLoadCallback;
import com.antran.expressfootball.matchesfragment.LastedHighlightFragment;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.util.CustomTypefaceSpan;
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
    //    private SubMenu leagueItems;
    private Picasso picasso;
    private ImageLoader imageLoader;

    public MenuView(NavigationView navigationView) {
        this.navigationView = navigationView;
        menu = navigationView.getMenu();
        applyFontToDefaultMenuItems();
//        picasso = Picasso.with(navigationView.getContext().getApplicationContext());
//        picasso.setIndicatorsEnabled(false);
//        picasso.setLoggingEnabled(true);
        imageLoader = MySingleton.getInstance(navigationView.getContext()).getImageLoader();


//        // Set the adapter for the list view
//        navigationView.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mPlanetTitles));
//        // Set the list's click listener
//        navigationView.setOnItemClickListener(new DrawerItemClickListener());
    }

//    public void setAdapter(ArrayAdapter<String> adapter){
//        navigationView.setAdapter(adapter);
//    }

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
        menu.add(R.id.league_group, itemId, Menu.NONE, title);

        final MenuItem menuItem = menu.findItem(itemId);
        menuItem.setTitle(title);
        menuItem.setCheckable(true);
        applyFontToMenuItem(menuItem);
        imageLoader.get(icon, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                menuItem.setIcon(new BitmapDrawable(navigationView.getResources(), response.getBitmap()));
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(navigationView.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        if (icon != null && icon.compareTo("") != 0){
//            ImageLoader imageLoader = new ImageLoader(navigationView.getResources(), icon, new ImageLoadCallback() {
//                @Override
//                public void onPreLoad() {
//                }
//
//                @Override
//                public void onLoadSuccessfully(Drawable drawable) {
//                    menuItem.setIcon(drawable);
//                }
//
//                @Override
//                public void onLoadFailure() {
//                    Toast.makeText(navigationView.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
//            imageLoader.execute();
//    }
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
        menu.removeGroup(R.id.league_group);
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(navigationView.getContext().getAssets(), "fonts/Roboto-Light.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

    private void applyFontToDefaultMenuItems() {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem mi = menu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }
}
