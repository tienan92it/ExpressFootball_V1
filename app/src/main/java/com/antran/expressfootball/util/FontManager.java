package com.antran.expressfootball.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import com.antran.expressfootball.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AnTran on 02/03/2016.
 */
public class FontManager {

    private static Typeface appFont;

    public static Typeface getAppFont(Context mContext) {
        if (appFont == null)
            appFont = Typeface.createFromAsset(mContext.getAssets(),
                    "fonts/Roboto-Regular.ttf");
        return appFont;
    }

    public static Typeface getFontFromAssets(Context mContext, String path) {
        return Typeface.createFromAsset(mContext.getAssets(), path);
    }

    public static SpannableString transferTextToFont(Context mContext, String text, String fontPath){
        Typeface font = getFontFromAssets(mContext, fontPath);
        SpannableString mNewText = new SpannableString(text);
        mNewText.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewText.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return mNewText;
    }

    private static FontManager sInstance;
    private Map<String, Typeface> mCache;

    private FontManager() {
        mCache = new HashMap<String, Typeface>();
    }

    public static FontManager getInstance() {
        if (sInstance == null) {
            sInstance = new FontManager();
        }

        return sInstance;
    }

    public void setFontTextView(TextView tv, AttributeSet attrs) {
        String fontName = getFontName(tv.getContext(), attrs);
        setFontTextView(tv, fontName);
    }

    public void setFontTextView(TextView tv, String fontName) {
        if (fontName == null) return;

        Typeface typeface = mCache.get(fontName);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(tv.getContext().getAssets(), fontName);
            mCache.put(fontName, typeface);
        }

        tv.setTypeface(typeface);
    }

    public static String getFontName(Context c, AttributeSet attrs) {
        TypedArray arr = c.obtainStyledAttributes(attrs, R.styleable.Fontify);
        String fontName = arr.getString(R.styleable.Fontify_font);
        arr.recycle();
        return fontName;
    }
}
