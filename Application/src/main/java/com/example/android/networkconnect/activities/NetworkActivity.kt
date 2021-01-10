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
package com.example.android.networkconnect.activities

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.android.networkconnect.R
import com.example.android.networkconnect.fragments.NetworkFragment
import com.example.android.networkconnect.utils.DownloadCallback
import com.example.android.networkconnect.utils.DownloadCallback.Progress

/**
 * Sample Activity demonstrating how to connect to the network and fetch raw
 * HTML. It uses a Fragment that encapsulates the network operations on an AsyncTask.
 *
 *
 * This sample uses a TextView to display output.
 */
class NetworkActivity : FragmentActivity(), DownloadCallback {
    // Reference to the TextView showing fetched data, so we can clear it with a button
    // as necessary.
    private var mDataText: TextView? = null

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private var mNetworkFragment: NetworkFragment? = null

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private var mDownloading = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)
        mDataText = findViewById<View>(R.id.data_text) as TextView
        mNetworkFragment = NetworkFragment.getInstance(supportFragmentManager, "https://www.google.com")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fetch_action -> {
                startDownload()
                return true
            }
            R.id.clear_action -> {
                finishDownloading()
                mDataText!!.text = ""
                return true
            }
        }
        return false
    }

    private fun startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment!!.startDownload()
            mDownloading = true
        }
    }

    override fun updateFromDownload(result: String?) {
        if (result != null) {
            mDataText!!.text = result
        } else {
            mDataText!!.text = getString(R.string.connection_error)
        }
    }

    private fun activeNetworkInfo(): NetworkInfo {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo!!
    }

    override fun finishDownloading() {
        mDownloading = false
        if (mNetworkFragment != null) {
            mNetworkFragment!!.cancelDownload()
        }
    }

    override val activeNetworkInfo: NetworkInfo?
        get() = activeNetworkInfo()

    override fun onProgressUpdate(progressCode: Int, percentComplete: Int) {
        when (progressCode) {
            Progress.ERROR -> {
            }
            Progress.CONNECT_SUCCESS -> {
            }
            Progress.GET_INPUT_STREAM_SUCCESS -> {
            }
            Progress.PROCESS_INPUT_STREAM_IN_PROGRESS -> mDataText!!.text = "$percentComplete%"
            Progress.PROCESS_INPUT_STREAM_SUCCESS -> {
            }
        }
    }
}