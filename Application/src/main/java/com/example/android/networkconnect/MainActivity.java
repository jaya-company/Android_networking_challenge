/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.networkconnect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.example.android.networkconnect.api.APIKt.RICK_AND_MARTY_API_BASE_URL;

/**
 * Sample Activity demonstrating how to connect to the network and fetch raw
 * HTML. It uses a Fragment that encapsulates the network operations on an AsyncTask.
 *
 * This sample uses a TextView to display output.
 */
public class MainActivity extends AppCompatActivity implements DownloadCallback {

    private ViewGroup container;
    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;
    private CharactersAdapter charactersAdapter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);
        charactersAdapter = new CharactersAdapter(new ArrayList<>());
        container = (ViewGroup) findViewById(R.id.activity_root);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        // Reference to the TextView showing fetched data, so we can clear it with a button
        // as necessary.
        RecyclerView mCharactersList = (RecyclerView) findViewById(R.id.characters_recycler);
        mCharactersList.setLayoutManager(new LinearLayoutManager(this));
        mCharactersList.setAdapter(charactersAdapter);
        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), RICK_AND_MARTY_API_BASE_URL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fetch_action:
                startDownload();
                return true;
            // Clear the text and cancel download.
            case R.id.clear_action:
                finishDownloading();
                charactersAdapter.addItems(new ArrayList<>());
                return true;
        }
        return false;
    }

    private void startDownload() {
        mProgressBar.setVisibility(View.VISIBLE);
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }

    @Override
    public void updateFromDownload(NetworkFragment.Result result) {
        if (result.mResultValue != null) {
            charactersAdapter.addItems(result.mResultValue.getResults());
        } else {
            Snackbar.make(container, R.string.connection_error, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void finishDownloading() {
        mProgressBar.setVisibility(View.GONE);
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                break;
            case Progress.CONNECT_SUCCESS:
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                mProgressBar.setProgress(percentComplete);
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                mProgressBar.setVisibility(View.GONE);
                break;
        }
    }
}
