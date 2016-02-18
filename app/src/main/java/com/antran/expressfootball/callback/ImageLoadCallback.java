package com.antran.expressfootball.callback;

import android.graphics.drawable.Drawable;

/**
 * Created by AnTran on 16/01/2016.
 */
public interface ImageLoadCallback {

    public void onPreLoad();

    public void onLoadSuccessfully(Drawable drawable);

    public void onLoadFailure();
}
