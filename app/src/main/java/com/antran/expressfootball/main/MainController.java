package com.antran.expressfootball.main;

import android.content.Context;

import com.antran.expressfootball.MainScreen;

/**
 * Created by AnTran on 13/12/2015.
 */
public class MainController implements MainViewListener{

    private Context context;
    private MainView mainView;
    private MainControllerListener listener;

    public MainController(Context context, MainView mainView, MainControllerListener ltn) {
        this.context = context;
        this.mainView = mainView;
        this.listener = ltn;
    }

    public void closeDrawers() {
        mainView.closeDrawers();
    }

    public void setTitle(String title) {
        mainView.setTitle(title);
    }
}
