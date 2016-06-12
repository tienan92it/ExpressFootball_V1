package com.antran.expressfootball.matchesfragment.matches;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.antran.expressfootball.R;
import com.antran.expressfootball.matchesfragment.matchitem.MatchItemController;
import com.antran.expressfootball.util.EndlessRecyclerOnScrollListener;

/**
 * Created by AnTran on 05/12/2015.
 */
public class MatchesView {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public MatchesView(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.recyclerView = (RecyclerView) swipeRefreshLayout.findViewById(R.id.recyclerview);
        this.recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this.recyclerView.getContext());
        this.recyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setListener(final MatchesViewListener ltn) {
        final EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                ltn.loadMore(current_page);
            }
        };
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                endlessRecyclerOnScrollListener.reset();
                ltn.onRefreshListener();
            }
        });

        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
    }

    public void setAdapter(RecyclerView.Adapter<MatchItemController> adapter) {
        recyclerView.setAdapter(adapter);
    }

    public boolean isRefreshing() {
        return swipeRefreshLayout.isRefreshing();
    }

    public void setRefreshComplete() {
        swipeRefreshLayout.setRefreshing(false);
    }

    public void startRefresh() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    public void hideView() {
        recyclerView.setVisibility(View.GONE);
    }

    public void showView() {
        recyclerView.setVisibility(View.VISIBLE);
    }
}
