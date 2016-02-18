package com.antran.expressfootball.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import com.antran.expressfootball.callback.ImageLoadCallback;
import com.larvalabs.svgandroid.SVG;
import com.larvalabs.svgandroid.SVGParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by AnTran on 16/01/2016.
 */
public class ImageLoader extends AsyncTask<Void, Void, Drawable> {

    private Resources res;
    private String stringUrl;
    private ImageLoadCallback callback;

    public ImageLoader(Resources res,String stringUrl, ImageLoadCallback callback) {
        this.res = res;
        this.stringUrl = stringUrl;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (callback != null)
            callback.onPreLoad();
    }

    @Override
    protected Drawable doInBackground(Void... params) {
        URL url = null;
        try {
            url = new URL(stringUrl);
            if (isSVGImage()) {
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                SVG svg = SVGParser.getSVGFromInputStream(inputStream);
                Drawable drawable = svg.createPictureDrawable();
                return drawable;
            } else {
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return new BitmapDrawable(res, bmp);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        super.onPostExecute(drawable);
        if (drawable != null) {
            callback.onLoadSuccessfully(drawable);
        } else {
            callback.onLoadFailure();
        }
    }

    private boolean isSVGImage() {
        return stringUrl.endsWith(".svg");
    }
}
