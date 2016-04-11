package com.antran.expressfootball.rankingfragment.tabs;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.antran.expressfootball.R;
import com.antran.expressfootball.callback.ImageLoadCallback;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.util.ImageLoader;

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

    public void addTab(String icon) {
        imageLoader.get(icon, new com.android.volley.toolbox.ImageLoader.ImageListener() {
            @Override
            public void onResponse(com.android.volley.toolbox.ImageLoader.ImageContainer response, boolean isImmediate) {
                tabLayout.addTab(tabLayout.newTab().setIcon(new BitmapDrawable(tabLayout.getResources(), response.getBitmap())));
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(tabLayout.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//        ImageLoader imageLoader = new ImageLoader(tabLayout.getResources(), icon, new ImageLoadCallback() {
//            @Override
//            public void onPreLoad() {
//            }
//
//            @Override
//            public void onLoadSuccessfully(Drawable drawable) {
//                tabLayout.addTab(tabLayout.newTab().setIcon(drawable));
//            }
//
//            @Override
//            public void onLoadFailure() {
//                Toast.makeText(tabLayout.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//        imageLoader.execute();
    }

    public void clearTabs() {
        tabLayout.removeAllTabs();
    }
}
