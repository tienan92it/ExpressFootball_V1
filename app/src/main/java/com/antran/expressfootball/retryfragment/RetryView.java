package com.antran.expressfootball.retryfragment;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.antran.expressfootball.R;

/**
 * Created by AnTran on 11/04/2016.
 */
public class RetryView {

    private RelativeLayout rootView;

    private Button retry;
    private ProgressBar progressBar;

    public RetryView(RelativeLayout root){
        rootView = root;

        retry = (Button) rootView.findViewById(R.id.retry_button);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);
        retry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void setListener(final RetryViewListener ltn){
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltn.onRetry();
            }
        });
    }

    public void showView() {
        rootView.setVisibility(View.VISIBLE);
    }

    public void hideView(){
        rootView.setVisibility(View.GONE);
    }

    public void showProgress() {
        retry.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        retry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }
}
