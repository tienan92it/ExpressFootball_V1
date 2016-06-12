package com.antran.expressfootball.favoritefragment.teamsfragment.teams;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.antran.expressfootball.favoritefragment.teamsfragment.team.TeamController;

/**
 * Created by AnTran on 12/03/2016.
 */
public class TeamsView {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public TeamsView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        linearLayoutManager = new LinearLayoutManager(this.recyclerView.getContext());
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setListener(TeamsViewListener ltn) {

    }

    public void setAdapter(RecyclerView.Adapter<TeamController> adapter) {
        recyclerView.setAdapter(adapter);
    }

    public void scrollToItem(int position){
        linearLayoutManager.scrollToPositionWithOffset(position, 50);
    }
}
