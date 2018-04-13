package com.example.ivanovnv.course02task03asyntaskloader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by IvanovNV on 13.04.2018.
 */

public class CustomAsyncTaskLoader extends AsyncTaskLoader <Boolean> {

    private static final String TAG = "CustomAsyncTaskLoader";
    private boolean mFlag = false;

    public CustomAsyncTaskLoader(Context context) {
        super(context);
    }

    @Override
    public Boolean loadInBackground() {
        Log.d(TAG, "loadInBackground: thread:"+ Thread.currentThread().getId());

        mFlag = true;

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        mFlag = false;
        return false;
    }

    @Override
    public void deliverResult(Boolean data) {
        mFlag = data;
        Log.d(TAG, "deliverResult: data:" + data);
        super.deliverResult(data);
    }
}

