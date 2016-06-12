package com.antran.expressfootball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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
 * Created by AnTran on 27/11/2015.
 */
public class SplashScreen extends Activity {

    private Activity activity;

    private ImageView imgBall;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.splash_screen_view);

        imgBall = (ImageView) findViewById(R.id.logo);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url = Global.getURLLeaguesWith(1, 15, "order", "asc");
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Leagues.getInstance(activity.getBaseContext()).importLeagues(response);
                            Intent main = new Intent(activity, MainScreen.class);
                            startActivity(main);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Intent main = new Intent(activity, MainScreen.class);
                        startActivity(main);
                        finish();
                    }
                });
        MySingleton.getInstance(activity.getBaseContext()).addToRequestQueue(jsonObjectRequest);
    }

}
