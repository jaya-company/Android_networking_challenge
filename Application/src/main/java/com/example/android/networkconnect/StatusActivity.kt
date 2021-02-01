package com.example.android.networkconnect

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.example.android.networkconnect.network.DownloadCallback
import com.example.android.networkconnect.network.NetworkFragment
import com.example.android.networkconnect.R
import com.example.android.networkconnect.datafragment.datainj.DaggerApiComponent

class StatusActivity: FragmentActivity(), DownloadCallback {

    // Reference to the TextView showing fetched data, so we can clear it with a button
    // as necessary.
    private var mDataText: TextView? = null

    // Keep a reference to the NetworkFragment which owns the AsyncTask object
    // that is used to execute network ops.
    private var mNetworkFragment: NetworkFragment? = null

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private var mDownloading = false

    override val activeNetworkInfo: NetworkInfo
        get() = activeNetworkInfo()

    private fun activeNetworkInfo(): NetworkInfo {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.activeNetworkInfo
        return networkInfo!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)
        mDataText = findViewById<View>(R.id.data_text) as TextView
        mNetworkFragment = NetworkFragment.getInstance(supportFragmentManager, "https://www.google.com")
        DaggerApiComponent.create().inject(this)
    }

    private fun startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment!!.startDownload()
            mDownloading = true
        }
    }

    override fun updateFromDownload(result: String) {
        if (result != null) {
            mDataText!!.text = result
        } else {
            mDataText!!.text = getString(R.string.connection_error)
        }
    }

    override fun onProgressUpdate(progressCode: Int, percentComplete: Int) {
        when (progressCode) {
            // You can add UI behavior for progress updates here.
            DownloadCallback.Progress.ERROR -> {
            }
            DownloadCallback.Progress.CONNECT_SUCCESS -> {
            }
            DownloadCallback.Progress.GET_INPUT_STREAM_SUCCESS -> {
            }
            DownloadCallback.Progress.PROCESS_INPUT_STREAM_IN_PROGRESS -> mDataText!!.text = "$percentComplete%"
            DownloadCallback.Progress.PROCESS_INPUT_STREAM_SUCCESS -> {
            }
        }
    }

    override fun finishDownloading() {
        mDownloading = false
        if (mNetworkFragment != null) {
            mNetworkFragment!!.cancelDownload()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // When the user clicks FETCH, fetch the first 500 characters of
            // raw HTML from www.google.com.
            R.id.fetch_action -> {
                startDownload()
                return true
            }
            // Clear the text and cancel download.
            R.id.clear_action -> {
                finishDownloading()
                mDataText!!.text = ""
                return true
            }
        }
        return false
    }
}