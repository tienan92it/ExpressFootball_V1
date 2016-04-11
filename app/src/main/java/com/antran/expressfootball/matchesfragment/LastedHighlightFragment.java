package com.antran.expressfootball.matchesfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.antran.expressfootball.R;
import com.antran.expressfootball.matchesfragment.matches.MatchesController;
import com.antran.expressfootball.matchesfragment.matches.MatchesControllerListener;
import com.antran.expressfootball.matchesfragment.matches.MatchesView;
import com.antran.expressfootball.loading.LoadingController;
import com.antran.expressfootball.loading.LoadingControllerListener;
import com.antran.expressfootball.loading.LoadingView;

/**
 * Created by AnTran on 05/12/2015.
 */
public class LastedHighlightFragment extends Fragment implements LoadingControllerListener, MatchesControllerListener {

    private Context context;

    private LoadingController loadingController;
    private MatchesController matchesController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.list_matches_fragment, container, false);

        LoadingView loadingView = new LoadingView(context);
        loadingController = new LoadingController(loadingView, this);

        MatchesView matchesView = new MatchesView((SwipeRefreshLayout) rootview.findViewById(R.id.root_view));
        matchesController = new MatchesController(context, matchesView, this);
        matchesView.setListener(matchesController);
        matchesView.setAdapter(matchesController);

        matchesController.firstLoadData(0);
        return rootview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onLoading() {
        loadingController.showLoading();
    }

    @Override
    public void onLoaded() {
        loadingController.hideLoading();
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
