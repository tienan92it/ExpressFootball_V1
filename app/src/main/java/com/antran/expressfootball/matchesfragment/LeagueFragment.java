package com.antran.expressfootball.matchesfragment;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antran.expressfootball.R;
import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;
import com.antran.expressfootball.matchesfragment.matches.MatchesController;
import com.antran.expressfootball.matchesfragment.matches.MatchesControllerListener;
import com.antran.expressfootball.matchesfragment.matches.MatchesView;
import com.antran.expressfootball.util.Key;
import com.antran.expressfootball.util.svg.SvgDecoder;
import com.antran.expressfootball.util.svg.SvgDrawableTranscoder;
import com.antran.expressfootball.util.svg.SvgSoftwareLayerSetter;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;

/**
 * Created by Appable on 1/10/2016.
 */
public class LeagueFragment extends Fragment implements LoadingControllerListener, MatchesControllerListener {

    private View rootview;
    private Context context;
    private int leagueId = 0;

    private LoadingController loadingController;
    private MatchesController matchesController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
        Bundle bundle = getArguments();
        if (bundle != null){
            leagueId = bundle.getInt(Key.LEAGUE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.list_matches_fragment, container, false);

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        MatchesView matchesView = new MatchesView((SwipeRefreshLayout) rootview.findViewById(R.id.root_view));
        matchesController = new MatchesController(context, matchesView, this);
        matchesView.setListener(matchesController);
        matchesView.setAdapter(matchesController);

        matchesController.firstLoadData(leagueId);
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
