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
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.networkconnect.model.CharacterResult;
import com.google.gson.Gson;

/**
 * Sample Activity demonstrating how to connect to the network and fetch raw
 * HTML. It uses a Fragment that encapsulates the network operations on an AsyncTask.
 *
 * This sample uses a TextView to display output.
 */
public class MainActivity extends FragmentActivity implements DownloadCallback {

    public static final String URL_TO_FETCH = "https://rickandmortyapi.com/api/character";

    private ProgressBar fetchProgress;

    // Reference to the RecyclerView showing fetched data, so we can clear it with a button
    // as necessary.
    private RecyclerView charactersList;

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_main);

        charactersList = findViewById(R.id.recyclerView_result);
        charactersList.setLayoutManager(new LinearLayoutManager(this));

        fetchProgress = findViewById(R.id.fetch_progress);

        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), URL_TO_FETCH);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // When the user clicks FETCH, fetch the first 500 characters of
            // raw HTML from www.google.com.
            case R.id.fetch_action:
                startDownload();
                return true;
            // Clear the text and cancel download.
            case R.id.clear_action:
                finishDownloading();
                charactersList.setAdapter(null);
                return true;
        }
        return false;
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            charactersList.setVisibility(View.GONE);
            fetchProgress.setVisibility(View.VISIBLE);

            // Execute the async download.
            mDownloading = true;
            mNetworkFragment.startDownload();
        }
    }

    @Override
    public void updateFromDownload(String result, String error) {
        if (result != null) {
            Gson gson = new Gson();
            CharacterResult characterResult = gson.fromJson(result, CharacterResult.class);
            charactersList.setAdapter(new CharactersAdapter(characterResult));
        } else {
            String errorToShow = getString(R.string.connection_error);

            if (error != null && !error.isEmpty()) {
                errorToShow = error;
            }

            Toast.makeText(this, errorToShow, Toast.LENGTH_SHORT).show();
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
        charactersList.setVisibility(View.VISIBLE);
        fetchProgress.setVisibility(View.GONE);

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
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                break;
        }
    }

}
