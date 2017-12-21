package com.onekey.zhougongjiemeng;

import mythware.common.CrashHandler;
import android.app.Application;

public class MyApp extends Application{

	public void onCreate() {
		super.onCreate();
		CrashHandler.getInstance().init(this);
	}
}
