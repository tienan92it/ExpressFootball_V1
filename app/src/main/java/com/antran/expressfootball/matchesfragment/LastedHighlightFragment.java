package com.antran.expressfootball.matchesfragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antran.expressfootball.R;
import com.antran.expressfootball.matchesfragment.matches.MatchesController;
import com.antran.expressfootball.matchesfragment.matches.MatchesControllerListener;
import com.antran.expressfootball.matchesfragment.matches.MatchesView;
import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;
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
 * Created by AnTran on 05/12/2015.
 */
public class LastedHighlightFragment extends Fragment implements LoadingControllerListener, MatchesControllerListener {

    private Context context;

    private LoadingController loadingController;
    private MatchesController matchesController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.lasted_high_light_fragment, container, false);

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        GenericRequestBuilder requestBuilder = Glide.with(getActivity())
                .using(Glide.buildStreamModelLoader(Uri.class, getActivity()), InputStream.class)
                .from(Uri.class)
                .as(SVG.class)
                .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                .sourceEncoder(new StreamEncoder())
                .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                .decoder(new SvgDecoder())
                .placeholder(R.drawable.ic_ball)
                .error(R.drawable.ic_ball)
                .animate(android.R.anim.fade_in)
                .listener(new SvgSoftwareLayerSetter<Uri>());

        MatchesView matchesView = new MatchesView((SwipeRefreshLayout) rootview.findViewById(R.id.root_view));
        matchesController = new MatchesController(context, matchesView, requestBuilder, this);
        matchesView.setListener(matchesController);
        matchesView.setAdapter(matchesController);

        matchesController.firstLoadData(0);
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
