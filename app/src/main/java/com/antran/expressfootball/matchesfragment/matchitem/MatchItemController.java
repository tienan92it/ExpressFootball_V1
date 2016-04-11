package com.antran.expressfootball.matchesfragment.matchitem;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antran.expressfootball.PlayerActivity;
import com.antran.expressfootball.YoutubeActivity;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.model.League;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.util.Key;
import com.antran.expressfootball.util.TypeVideo;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchItemController extends RecyclerView.ViewHolder implements MatchItemViewListener {

    private MatchInfo matchInfo;
    private League league;
    private Team home;
    private Team away;
    private MatchItemView matchItemView;

    public MatchItemController(MatchItemView itemView) {
        super(itemView.getContainer());
        matchItemView = itemView;
        matchInfo = new MatchInfo();
        league = new League();
        home = new Team();
        away = new Team();
    }

    public void bindData(MatchInfo matchInfo) {
        this.matchInfo = matchInfo;
        matchItemView.bindData(matchInfo);
//        updateLeague();
//        updateHomeTeam();
//        updateAwayTeam();
    }

    @Override
    public void onClickMatch(View v) {
        switch (matchInfo.getLinkType()) {
            case TypeVideo.VIDEO_PLAYER:
                Intent mpdIntent = new Intent(v.getContext(), PlayerActivity.class)
                        .setData(Uri.parse(matchInfo.getLinkVideo()))
                        .putExtra(PlayerActivity.CONTENT_ID_EXTRA, "")
                        .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, PlayerActivity.TYPE_OTHER)
                        .putExtra(PlayerActivity.PROVIDER_EXTRA, "")
                        .putExtra(PlayerActivity.TITLE_EXTRA, matchInfo.getTitle());
                v.getContext().startActivity(mpdIntent);
                break;
            case TypeVideo.E_YOUTUBE:
                Intent youtubeIntent = new Intent(v.getContext(), YoutubeActivity.class)
                        .putExtra(Key.VIDEO_LINK, matchInfo.getLinkVideo());
                v.getContext().startActivity(youtubeIntent);
                break;
            default:
                break;
        }
    }
}
