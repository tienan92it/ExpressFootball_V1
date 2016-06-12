package com.antran.expressfootball.rankingfragment.tablefragment.rows;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antran.expressfootball.rankingfragment.tablefragment.RowInfo;
import com.antran.expressfootball.rankingfragment.tablefragment.row.RowController;
import com.antran.expressfootball.rankingfragment.tablefragment.row.RowView;

/**
 * Created by AnTran on 06/03/2016.
 */
public class RowsView {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public RowsView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        linearLayoutManager = new LinearLayoutManager(this.recyclerView.getContext());
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setAdapter(RecyclerView.Adapter<RowController> adapter){
        recyclerView.setAdapter(adapter);
    }

    public void setListener(RowsViewListener ltn){}

    public void showView() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    public void hideView() {
        recyclerView.setVisibility(View.GONE);
    }
}
