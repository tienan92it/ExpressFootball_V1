package com.antran.expressfootball.loading;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingView extends ProgressDialog {

	public LoadingView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.setMessage("Loading...");
		this.setCancelable(false);
	}

}
