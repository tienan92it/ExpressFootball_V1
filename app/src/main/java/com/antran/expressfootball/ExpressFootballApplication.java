package com.antran.expressfootball;

import android.app.Application;
import android.support.annotation.Keep;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

/**
 * Created by AnTran on 12/12/2015.
 */
public class ExpressFootballApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "1ip2FtyCc3h346mfujoYCiiVO6yX8r8JTt1FTCcq", "Sgl5vInE8i9AqcwTPJakJb0sSfoIJcV2vKbU8MuL");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
