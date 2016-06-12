package com.antran.expressfootball.matchesfragment.matchitem;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.antran.expressfootball.R;
import com.antran.expressfootball.matchesfragment.MatchInfo;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.widget.TextViewWithFont;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.caverock.androidsvg.SVG;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchItemView {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("kk:mm");

    private static final int LAYOUT_ITEM = R.layout.match_item;

    private View container;
    private LinearLayout cardView;
    private TextViewWithFont title;
    private TextViewWithFont detail;
    private TextViewWithFont date;
    private TextViewWithFont time;
    private NetworkImageView thumbnail;
    private Picasso picasso;
    private GenericRequestBuilder<Uri, InputStream, SVG, Bitmap> requestBuilder;
    private ImageLoader imageLoader;

    public MatchItemView(ViewGroup parent) {
        container = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ITEM, parent, false);

        cardView = (LinearLayout) container.findViewById(R.id.cardview);
        title = (TextViewWithFont) container.findViewById(R.id.title);
        title.setSelected(true);
        detail = (TextViewWithFont) container.findViewById(R.id.detail);
        date = (TextViewWithFont) container.findViewById(R.id.date);
        time = (TextViewWithFont) container.findViewById(R.id.time);
        thumbnail = (NetworkImageView) container.findViewById(R.id.thumbnail);
        thumbnail.setDefaultImageResId(R.drawable.video_placeholder);

        picasso = Picasso.with(parent.getContext().getApplicationContext());
        picasso.setIndicatorsEnabled(false);

        // Get the ImageLoader through your singleton class.
        imageLoader = MySingleton.getInstance(parent.getContext()).getImageLoader();
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
        if (matchInfo.getThumbnail() != null && matchInfo.getThumbnail().compareTo("") != 0) {
            thumbnail.setImageUrl(matchInfo.getThumbnail(), imageLoader);
        } else
            thumbnail.setImageResource(R.drawable.video_placeholder);
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
