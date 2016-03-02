package com.antran.expressfootball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.antran.expressfootball.util.FontManager;

public class TextViewWithFont extends TextView {

	protected Context mContext;
	
	public TextViewWithFont(Context context) {
		super(context);
		mContext = context;
		// TODO Auto-generated constructor stub
		if(!isInEditMode()){
			initFont();
		}
	}
	
	public TextViewWithFont(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		// TODO Auto-generated constructor stub
		if(!isInEditMode()){
			FontManager.getInstance().setFontTextView(this, attrs);
		}
	}
	
	public TextViewWithFont(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		// TODO Auto-generated constructor stub
		if(!isInEditMode()){
			FontManager.getInstance().setFontTextView(this, attrs);
		}
	}

	private void initFont(){
		this.setTypeface(FontManager.getAppFont(mContext));
	}
}
