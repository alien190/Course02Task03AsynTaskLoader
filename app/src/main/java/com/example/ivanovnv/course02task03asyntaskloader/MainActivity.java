package com.example.ivanovnv.course02task03asyntaskloader;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Boolean>{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Boolean> onCreateLoader(int id, Bundle args) {
        return new AsyncTaskLoader<Boolean>(this){
            @Override
            public Boolean loadInBackground() {
                return false;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Boolean> loader, boolean data) {

    }

    @Override
    public void onLoaderReset(Loader<Boolean> loader) {

    }
}
