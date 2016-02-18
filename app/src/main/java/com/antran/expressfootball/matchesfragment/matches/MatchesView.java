package com.antran.expressfootball.matchesfragment.matches;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.antran.expressfootball.R;
import com.antran.expressfootball.util.EndlessRecyclerOnScrollListener;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchesView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private ProgressBar progressBar;

    public MatchesView(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.recyclerView = (RecyclerView) swipeRefreshLayout.findViewById(R.id.recyclerview);
        this.recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this.recyclerView.getContext());
        this.recyclerView.setLayoutManager(linearLayoutManager);
        progressBar = (ProgressBar) swipeRefreshLayout.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
    }

    public void setListener(final MatchesViewListener ltn) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ltn.onRefreshListener();
            }
        });

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                ltn.loadMore(current_page);
            }
        });
    }

    public void setAdapter(MatchesController adapter) {
        recyclerView.setAdapter(adapter);
    }

    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    public void setRefreshComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void showProgressLoadMore() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgressLoadMore() {
        progressBar.setVisibility(View.GONE);
    }
}
