package com.antran.expressfootball.favoritefragment.teamsfragment.team;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.antran.expressfootball.R;
import com.antran.expressfootball.model.Team;
import com.antran.expressfootball.networkrequest.MySingleton;
import com.antran.expressfootball.widget.TextViewWithFont;

/**
 * Created by AnTran on 12/03/2016.
 */
public class TeamView {

    private static final int LAYOUT_ITEM = R.layout.favorite_team_item;

    private View container;

    private NetworkImageView logo;
    private TextViewWithFont name;
    private ImageLoader imageLoader;

    public TeamView(ViewGroup parent) {
        container = LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ITEM, parent, false);
        logo = (NetworkImageView) container.findViewById(R.id.logo);
        name = (TextViewWithFont) container.findViewById(R.id.name);

        // Get the ImageLoader through your singleton class.
        imageLoader = MySingleton.getInstance(parent.getContext()).getImageLoader();
    }

    public View getContainer() {
        return container;
    }

    public void setListener(final TeamViewListener ltn) {
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltn.onTeamSelected();
            }
        });
    }

    public void bindData(Team team) {
        name.setText(team.getTeamName());
        logo.setImageUrl(team.getCrestUrl(), imageLoader);
    }
}
