package com.antran.expressfootball.rankingfragment.tablefragment.row;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.antran.expressfootball.R;
import com.antran.expressfootball.callback.ImageLoadCallback;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.rankingfragment.tablefragment.RowInfo;
import com.antran.expressfootball.util.ImageLoader;
import com.antran.expressfootball.widget.TextViewWithFont;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGImageView;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by AnTran on 06/03/2016.
 */
public class RowView {

    private static final int LAYOUT_ITEM = R.layout.ranking_table_row;

    public View getContainer() {
        return container;
    }

    private View container;
    private LinearLayout cardView;
    private TextViewWithFont number;
    private NetworkImageView clubLogoPNG;
    private TextViewWithFont clubName;
    private TextViewWithFont playedGame;
    private TextViewWithFont goalDifference;
    private TextViewWithFont points;

    private com.android.volley.toolbox.ImageLoader imageLoader;

    public RowView(ViewGroup parent){
        container = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ITEM, parent, false);
        cardView = (LinearLayout) container.findViewById(R.id.cardview);
        number = (TextViewWithFont) container.findViewById(R.id.number);
        clubLogoPNG = (NetworkImageView) container.findViewById(R.id.club_logo_png);
        clubName = (TextViewWithFont) container.findViewById(R.id.club_name);
        playedGame = (TextViewWithFont) container.findViewById(R.id.played_game);
        goalDifference = (TextViewWithFont) container.findViewById(R.id.goal_diff);
        points = (TextViewWithFont) container.findViewById(R.id.point);
        // Get the ImageLoader through your singleton class.
        imageLoader = MySingleton.getInstance(parent.getContext()).getImageLoader();
    }

    public void setListener(RowViewListener ltn) {

    }

    public void bindData(final RowInfo rowInfo) {
        number.setText(String.valueOf(rowInfo.getPosition()));
        clubLogoPNG.setImageUrl(rowInfo.getLogo(), imageLoader);
//        if (rowInfo.getLogo() != null && rowInfo.getLogo().compareTo("") != 0){
//            ImageLoader imageLoader = new ImageLoader(container.getResources(), rowInfo.getLogo(), new ImageLoadCallback() {
//                @Override
//                public void onPreLoad() {
//                }
//
//                @Override
//                public void onLoadSuccessfully(Drawable drawable) {
//                    clubLogoPNG.setImageDrawable(drawable);
//                }
//
//                @Override
//                public void onLoadFailure() {
//                    Toast.makeText(container.getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
//                }
//            });
//            imageLoader.execute();
//        }

        clubName.setText(String.valueOf(rowInfo.getClubName()));
        playedGame.setText(String.valueOf(rowInfo.getPlayedGame()));
        goalDifference.setText(String.valueOf(rowInfo.getGoalDifference()));
        points.setText(String.valueOf(rowInfo.getPoints()));
//        setClubLogoPNG(rowInfo.getLogo());
    }

}
