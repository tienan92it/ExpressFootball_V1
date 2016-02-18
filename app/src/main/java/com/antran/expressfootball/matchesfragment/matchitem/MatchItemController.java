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
import com.antran.expressfootball.service.LeagueDataHelper;
import com.antran.expressfootball.service.TeamDataHelper;
import com.antran.expressfootball.util.Key;
import com.antran.expressfootball.util.TypeVideo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.List;

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
//        Intent video = new Intent(v.getContext(), FootballVideoScreen.class);
//        video.putExtra(Key.VIDEO_LINK, matchInfo.getLinkVideo());
//        v.getContext().startActivity(video);
        switch (matchInfo.getLinkType()) {
            case TypeVideo.VIDEO_PLAYER:
                Intent mpdIntent = new Intent(v.getContext(), PlayerActivity.class)
                        .setData(Uri.parse(matchInfo.getLinkVideo()))
                        .putExtra(PlayerActivity.CONTENT_ID_EXTRA, "")
                        .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, PlayerActivity.TYPE_OTHER)
                        .putExtra(PlayerActivity.PROVIDER_EXTRA, "");
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

//    private void updateLeague() {
//        LeagueDataHelper.getLeagueFromId(matchInfo.getLeagueId(), new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null && objects.size() > 0) {
//                    league = League.parse(objects.get(0));
//                    matchItemView.setDetail(matchInfo.getRound() + " - " + league.getLeagueName());
//                }
//            }
//        });
//    }
//
//    private void updateHomeTeam() {
//        TeamDataHelper.getTeamFromId(matchInfo.getHomeTeamId(), new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null && objects.size() > 0) {
//                    home = Team.parse(objects.get(0));
//                    matchItemView.setTitle(home.getTeamName() + " " + matchInfo.getHomeTeamScore() + " - "
//                            + " " + away.getTeamName() + " " + matchInfo.getAwayTeamId());
//                }
//            }
//        });
//    }
//
//    private void updateAwayTeam() {
//        TeamDataHelper.getTeamFromId(matchInfo.getAwayTeamId(), new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if (e == null && objects.size() > 0){
//                    away = Team.parse(objects.get(0));
//                    matchItemView.setTitle(home.getTeamName() + " " + matchInfo.getHomeTeamScore() + " - "
//                            + " " + away.getTeamName() + " " + matchInfo.getAwayTeamId());
//                }
//            }
//        });
//    }
}
