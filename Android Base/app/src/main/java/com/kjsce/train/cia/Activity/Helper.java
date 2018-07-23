package com.kjsce.train.cia.Activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/**
 * Created by deepmehta on 17/02/18.
 */

public class Helper {

    public Context c;
    public Helper(Context c){
        this.c = c;
    }

    public boolean isNetworkConnected() {
        /*ConnectivityManager connectivityManager
                = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();*/
        return true;
    }

    public void clearData(){
        ContextWrapper contextWrapper = new ContextWrapper(c);
        File myDir=contextWrapper.getFilesDir();
        deleteRecursive(myDir);
        File mainDir = Environment.getExternalStorageDirectory();
        File mainFile = new File(mainDir, "/CIA/Inspection/");
        deleteRecursive(mainFile);
    }

    void deleteRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();

    }

    public String encodeStringToURLSafe(String pathParam) {
        try {
            return URLEncoder.encode(pathParam, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return pathParam;
        }
    }

    public String decodeStringToURLSafe(String pathParam) {
        try {
            return URLDecoder.decode(pathParam, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return pathParam;
        }
    }

}