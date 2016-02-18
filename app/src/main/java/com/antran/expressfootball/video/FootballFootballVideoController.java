package com.antran.expressfootball.video;

import android.content.Context;

/**
 * Created by AnTran on 12/12/2015.
 */
public class FootballFootballVideoController implements FootballVideoViewListener {

    private Context context;
    private String videoLink;
    private FootballVideoView footballVideoView;
    private FootballVideoControllerListener listener;

    public FootballFootballVideoController(Context context, FootballVideoView footballVideoView, FootballVideoControllerListener ltn) {
        this.context = context;
        this.footballVideoView = footballVideoView;
        this.listener = ltn;
        this.videoLink = "";
    }

    public void setVideoLink(String videoLink) {
        footballVideoView.setLink(videoLink);
    }
}
