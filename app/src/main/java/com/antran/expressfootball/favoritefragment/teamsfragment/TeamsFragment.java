package com.antran.expressfootball.favoritefragment.teamsfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antran.expressfootball.MainScreen;
import com.antran.expressfootball.R;
import com.antran.expressfootball.favoritefragment.teamsfragment.teams.TeamsController;
import com.antran.expressfootball.favoritefragment.teamsfragment.teams.TeamsControllerListener;
import com.antran.expressfootball.favoritefragment.teamsfragment.teams.TeamsView;
import com.antran.expressfootball.util.Key;

/**
 * Created by AnTran on 12/03/2016.
 */
public class TeamsFragment extends Fragment implements TeamsControllerListener {

    private Context context;
    private TeamsController teamsController;
    private int leagueId;
    private MainScreen activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getContext();
        activity = (MainScreen) getActivity();
        Bundle bundle = getArguments();
        if (bundle != null) {
            leagueId = bundle.getInt(Key.LEAGUE_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.favorite_team_fragment, container, false);

        TeamsView teamsView = new TeamsView((RecyclerView) rootView.findViewById(R.id.list_of_team));
        teamsController = new TeamsController(context, teamsView, this);
        teamsView.setListener(teamsController);
        teamsView.setAdapter(teamsController);

        teamsController.loadData(leagueId);
        return rootView;
    }

    @Override
    public void teamSelected() {
        activity.onClickMyFavorite();
    }
}
