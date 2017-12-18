package com.github.xuyafan.lifelog;

import android.app.Application;

import org.litepal.LitePal;

/**
 * Created by xyf18 on 2017/12/14.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }


}
