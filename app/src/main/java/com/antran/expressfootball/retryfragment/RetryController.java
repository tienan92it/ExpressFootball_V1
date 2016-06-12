package com.antran.expressfootball.retryfragment;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.antran.expressfootball.model.Leagues;
import com.antran.expressfootball.networkrequest.Global;
import com.antran.expressfootball.networkrequest.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by AnTran on 11/04/2016.
 */
public class RetryController implements RetryViewListener {

    private Context context;
    private RetryView retryView;
    private RetryControllerListener listener;

    public RetryController(Context context, RetryView retryView, RetryControllerListener listener) {
        this.context = context;
        this.retryView = retryView;
        this.listener = listener;
    }

    public void show() {
        retryView.showView();
    }

    @Override
    public void onRetry() {
        if (Leagues.getInstance(context).getLeagues().size() == 0) {
            retryView.showProgress();
            String url = Global.getURLLeaguesWith(1, 15, "order", "asc");
            JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            try {
                                Leagues.getInstance(context).importLeagues(response);
                                listener.onRetry();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                retryView.hideProgress();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            retryView.hideProgress();
                        }
                    });
            MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
        } else listener.onRetry();
    }

    public void hide() {
        retryView.hideView();
    }
}
