package com.antran.expressfootball.favoritefragment.tabs;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.antran.expressfootball.networkrequest.MySingleton;

/**
 * Created by AnTran on 05/03/2016.
 */
public class TabsView {

    private TabLayout tabLayout;

    private com.android.volley.toolbox.ImageLoader imageLoader;

    public TabsView(TabLayout tabLayout) {
        this.tabLayout = tabLayout;
    }

    public void setViewPager(ViewPager viewPager) {
        tabLayout.setupWithViewPager(viewPager);
        imageLoader = MySingleton.getInstance(viewPager.getContext()).getImageLoader();
    }

    public void setListener(TabsViewListener ltn) {

    }

    public void addTab(final String icon, final int position) {
        imageLoader.get(icon, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                BitmapDrawable iconBtm = new BitmapDrawable(tabLayout.getResources(), response.getBitmap());
                iconBtm.setColorFilter(Color.parseColor("#ffcc00"), PorterDuff.Mode.SRC_IN);
                if (tabLayout.getTabCount() > position)
                    tabLayout.getTabAt(position).setIcon(iconBtm);
                else
                    tabLayout.addTab(tabLayout.newTab().setIcon(iconBtm));
            }

            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(tabLayout.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                error.printStackTrace();
            }
        });
    }

    public void selectTab(int position) {
        if (tabLayout.getTabCount() > position) {
            TabLayout.Tab tab = tabLayout.getTabAt(position);
            tab.select();
        }
    }

    public void clearTabs() {
        tabLayout.removeAllTabs();
    }
}
