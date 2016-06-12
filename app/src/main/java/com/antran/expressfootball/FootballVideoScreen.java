package com.antran.expressfootball;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;
import com.antran.expressfootball.util.Key;
import com.antran.expressfootball.video.FootballFootballVideoController;
import com.antran.expressfootball.video.FootballVideoControllerListener;
import com.antran.expressfootball.video.FootballVideoView;

/**
 * Created by AnTran on 12/12/2015.
 */
public class FootballVideoScreen extends Activity implements LoadingControllerListener, FootballVideoControllerListener {

    private Context context;
    private LoadingController loadingController;
    private FootballFootballVideoController footballVideoController;

    private String videoLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_screen);
        context = this;

        videoLink = getIntent().getStringExtra(Key.VIDEO_LINK);

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        FootballVideoView footballVideoView = new FootballVideoView(findViewById(R.id.swipe_refresh), videoLink);
        footballVideoController = new FootballFootballVideoController(context, footballVideoView, this);
        footballVideoView.setListener(footballVideoController);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
