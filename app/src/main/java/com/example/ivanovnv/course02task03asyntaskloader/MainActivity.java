package com.example.ivanovnv.course02task03asyntaskloader;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.LoaderManager;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Boolean>{

    public static final int LOADER_ID = 1;
    private Button mButton;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private static final String TAG = "MainActivity";
    Loader<Boolean> mLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.Button);
        mTextView = findViewById(R.id.TextView);
        mProgressBar = findViewById(R.id.ProgressBar);

        mLoader = getSupportLoaderManager().initLoader(LOADER_ID, null, this);
        Log.d(TAG, "onCreate: mLoader.isStarted:" + mLoader.isStarted());
        if (mLoader.isStarted()) {
            setLoadState();
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoadState();
                mLoader.onContentChanged();
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Loader<Boolean> onCreateLoader(int id, Bundle args) {
        if (id == LOADER_ID) {
            return new CustomAsyncTaskLoader(this);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Boolean> loader, Boolean data) {
        Log.d(TAG, "onLoadFinished: thread:" + Thread.currentThread().getId() + " data:" + data);
        setReadyState();
    }

    @Override
    public void onLoaderReset(Loader<Boolean> loader) {

    }

    private void setLoadState() {
        mButton.setEnabled(false);
        mTextView.setText(R.string.Loading);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void setReadyState() {
        mButton.setEnabled(true);
        mTextView.setText(R.string.Ready);
        mProgressBar.setVisibility(View.GONE);
    }

}
