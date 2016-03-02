package com.antran.expressfootball.matchesfragment.matchitem;

import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antran.expressfootball.R;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.widget.TextViewWithFont;
import com.bumptech.glide.GenericRequestBuilder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchItemView {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("kk:mm");

    private static final int LAYOUT_ITEM = R.layout.match_item;

    private View container;
    private CardView cardView;
    private TextViewWithFont title;
    private TextViewWithFont detail;
    private TextViewWithFont date;
    private TextViewWithFont time;
    //    private ImageView homeLogo;
//    private TextView homeScore;
//    private ImageView awayLogo;
//    private TextView awayScore;
//    private TextView league;
//    private TextView stadium;
//    private TextView round;
    private ImageView thumbnail;
    private Picasso picasso;
    private GenericRequestBuilder requestBuilder;

    public MatchItemView(ViewGroup parent, GenericRequestBuilder requestBuilder) {
        container = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ITEM, parent, false);

        cardView = (CardView) container.findViewById(R.id.cardview);
        title = (TextViewWithFont) container.findViewById(R.id.title);
        title.setSelected(true);
        detail = (TextViewWithFont) container.findViewById(R.id.detail);
        date = (TextViewWithFont) container.findViewById(R.id.date);
        time = (TextViewWithFont) container.findViewById(R.id.time);
        thumbnail = (ImageView) container.findViewById(R.id.thumbnail);
//        homeLogo = (ImageView) container.findViewById(R.id.home_logo);
//        homeLogo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        homeScore = (TextView) container.findViewById(R.id.home_score);
//        awayLogo = (ImageView) container.findViewById(R.id.away_logo);
//        awayLogo.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        awayScore = (TextView) container.findViewById(R.id.away_score);
//        league = (TextView) container.findViewById(R.id.league);
//        stadium = (TextView) container.findViewById(R.id.stadium);
//        round = (TextView) container.findViewById(R.id.round);

        picasso = Picasso.with(parent.getContext().getApplicationContext());
        picasso.setIndicatorsEnabled(false);
//        picasso.setLoggingEnabled(true);

        this.requestBuilder = requestBuilder;
    }

    public View getContainer() {
        return container;
    }

    public void bindData(MatchInfo matchInfo) {
        if (matchInfo.getTitle() != null && matchInfo.getTitle().compareTo("") != 0) {
            title.setText(matchInfo.getTitle());
        } else
            title.setText(matchInfo.getHomeTeam().getTeamName() + " " + matchInfo.getHomeTeamScore() + " - "
                    + " " + matchInfo.getAwayTeamScore() + " " + matchInfo.getAwayTeam().getTeamName());
        if (matchInfo.getRound() != 0)
            detail.setText(matchInfo.getRound() + " - " + matchInfo.getLeague().getLeagueName());
        else
            detail.setText(matchInfo.getLeague().getLeagueName());
        date.setText(DATE_FORMAT.format(matchInfo.getDate()));
        time.setText(TIME_FORMAT.format(matchInfo.getDate()));
        if (matchInfo.getThumbnail() != null && matchInfo.getThumbnail().compareTo("") != 0)
            picasso.load(matchInfo.getThumbnail()).placeholder(R.drawable.video_placeholder).into(thumbnail);
        else
            thumbnail.setImageResource(R.drawable.video_placeholder);
//        homeScore.setText(String.valueOf(matchInfo.getHomeTeamScore()));
//        awayScore.setText(String.valueOf(matchInfo.getAwayTeamScore()));
//        league.setText(matchInfo.getLeagueId().getLeagueName());
//        stadium.setText(matchInfo.getStadium());
//        round.setText(String.valueOf(matchInfo.getRound()));
//        String homeLogoUrl = matchInfo.getHomeTeamId().getCrestUrl();
//        String awayLogoUrl = matchInfo.getAwayTeamId().getCrestUrl();
//        if (homeLogoUrl.endsWith(".svg"))
//            requestBuilder
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            // SVG cannot be serialized so it's not worth to cache it
//                    .load(Uri.parse(homeLogoUrl))
//                    .into(homeLogo);
//        else
//            picasso.load(homeLogoUrl).placeholder(R.drawable.ic_ball).into(homeLogo);
//
//        if (awayLogoUrl.endsWith(".svg"))
//            requestBuilder
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                            // SVG cannot be serialized so it's not worth to cache it
//                    .load(Uri.parse(awayLogoUrl))
//                    .into(awayLogo);
//        else
//            picasso.load(awayLogoUrl).placeholder(R.drawable.ic_ball).into(awayLogo);
    }

    public void setListener(final MatchItemViewListener ltn) {
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltn.onClickMatch(v);
            }
        });
    }

    public void setDetail(String detail) {
        this.detail.setText(detail);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
