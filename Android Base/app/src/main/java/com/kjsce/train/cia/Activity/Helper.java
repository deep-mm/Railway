package com.kjsce.train.cia.Activity;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by deepmehta on 17/02/18.
 */

public class Helper {

    public Context c;
    public Helper(Context c){
        this.c = c;
    }

    public boolean isNetworkConnected() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}