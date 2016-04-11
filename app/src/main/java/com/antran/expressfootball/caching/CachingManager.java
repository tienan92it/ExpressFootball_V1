package com.antran.expressfootball.caching;

import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class CachingManager {

	private Context mContext;
	private static CachingManager instance;
	private SharedPreferences prefManager;
	private SharedPreferences.Editor prefEditor;

	private CachingManager(Context context) {
		mContext = context;
		prefManager = PreferenceManager.getDefaultSharedPreferences(context);
		prefEditor = prefManager.edit();
	}

	public static CachingManager getInstance(Context context) {
		if (instance == null)
			instance = new CachingManager(context);
		return instance;
	}

	public String getStringCached(String name, String defaultValue) {
		return prefManager.getString(name, defaultValue);
	}

	public void cachesString(String name, String value) {
		prefEditor.putString(name, value);
		prefEditor.commit();
	}

	public int getIntCached(String name, int defaultValue) {
		return prefManager.getInt(name, defaultValue);
	}

	public void cachesInt(String name, int value) {
		prefEditor.putInt(name, value);
		prefEditor.commit();
	}

	public long getLongCached(String name, long defaultValue) {
		return prefManager.getLong(name, defaultValue);
	}

	public void cachesLong(String name, long value) {
		prefEditor.putLong(name, value);
		prefEditor.commit();
	}

	public Set<String> getStringSet(String name, Set<String> defaultValue) {
		return prefManager.getStringSet(name, defaultValue);
	}

	public void cachesStringSet(String name, Set<String> value) {
		prefEditor.putStringSet(name, value);
		prefEditor.commit();
	}
	
	public boolean getBoolean(String name, boolean defaultValue) {
		return prefManager.getBoolean(name, defaultValue);
	}
	
	public void cachesBoolean(String name, boolean value){
		prefEditor.putBoolean(name, value);
		prefEditor.commit();
	}

	public void deleteCache(Context context) {
		prefEditor.clear().commit();
	}

}
