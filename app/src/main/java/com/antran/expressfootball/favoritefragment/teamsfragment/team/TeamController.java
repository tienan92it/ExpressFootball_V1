package com.antran.expressfootball.favoritefragment.teamsfragment.team;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antran.expressfootball.caching.CachingContant;
import com.antran.expressfootball.caching.CachingManager;
import com.antran.expressfootball.model.Team;
import com.google.gson.Gson;

/**
 * Created by AnTran on 12/03/2016.
 */
public class TeamController extends RecyclerView.ViewHolder implements TeamViewListener{

    private TeamView teamView;
    private Team team;
    private TeamControllerListener listener;

    public TeamController(TeamView itemView, TeamControllerListener listener) {
        super(itemView.getContainer());
        teamView = itemView;
        this.listener = listener;
        team = new Team();
    }

    public void bindData(Team team, boolean isFavorite) {
        this.team = team;
        teamView.bindData(team);
        if (isFavorite)
            teamView.hoverTeam();
        else teamView.noHoverTeam();
    }

    @Override
    public void onTeamSelected() {
        listener.teamSelected(team);
    }
}
