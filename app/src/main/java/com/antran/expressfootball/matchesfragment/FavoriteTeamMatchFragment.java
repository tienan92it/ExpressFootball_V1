package com.antran.expressfootball.matchesfragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.antran.expressfootball.MainScreen;
import com.antran.expressfootball.R;
import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;
import com.antran.expressfootball.matchesfragment.matches.MatchesByTeamController;
import com.antran.expressfootball.matchesfragment.matches.MatchesController;
import com.antran.expressfootball.matchesfragment.matches.MatchesControllerListener;
import com.antran.expressfootball.matchesfragment.matches.MatchesView;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.util.Key;
import com.antran.expressfootball.util.svg.SvgDecoder;
import com.antran.expressfootball.util.svg.SvgDrawableTranscoder;
import com.antran.expressfootball.util.svg.SvgSoftwareLayerSetter;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;
import com.google.gson.Gson;

import java.io.InputStream;

/**
 * Created by AnTran on 26/03/2016.
 */
public class FavoriteTeamMatchFragment extends Fragment implements LoadingControllerListener, MatchesControllerListener {

    private View rootview;
    private Context context;
    private MainScreen activity;
    private ImageView fab;

    private LoadingController loadingController;
    private MatchesByTeamController matchesController;
    private Team favoriteTeam;

    private ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
        activity = (MainScreen) getActivity();
        String teamJson = CachingManager.getInstance(context).getStringCached(CachingContant.FAVORITE_TEAM, "");
        Gson gson = new Gson();
        favoriteTeam = gson.fromJson(teamJson, Team.class);
        imageLoader = MySingleton.getInstance(context).getImageLoader();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.list_matches_favorite, container, false);

        fab = (ImageView) rootview.findViewById(R.id.fab);
        imageLoader.get(favoriteTeam.getCrestUrl(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                fab.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CachingManager.getInstance(context).cachesString(CachingContant.FAVORITE_TEAM, null);
                activity.openFavoriteTeamsSelect();
            }
        });

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        MatchesView matchesView = new MatchesView((SwipeRefreshLayout) rootview.findViewById(R.id.root_view));
        matchesController = new MatchesByTeamController(context, matchesView, this);
        matchesView.setListener(matchesController);
        matchesView.setAdapter(matchesController);

        matchesController.firstLoadData(favoriteTeam);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}