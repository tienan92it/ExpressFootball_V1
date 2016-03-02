package com.antran.expressfootball;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

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
    protected void onStart() {
        super.onStart();

        AsyncTask<Void, Void, Void> rotateBall = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                imgBall.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate));
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent main = new Intent(activity, MainScreen.class);
                startActivity(main);
                finish();
            }
        };

        rotateBall.execute();
    }
}
