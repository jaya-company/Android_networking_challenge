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

package com.example.android.networkconnect.network

import android.net.NetworkInfo

/**
 * Sample interface containing bare minimum methods needed for an asynchronous task
 * to update the UI Context.
 */

interface DownloadCallback {

    /**
     * Get the device's active network status in the form of a NetworkInfo object.
     */
    val activeNetworkInfo: NetworkInfo

    interface Progress {
        companion object {
            const val ERROR = -1
            const val CONNECT_SUCCESS = 0
            const val GET_INPUT_STREAM_SUCCESS = 1
            const val PROCESS_INPUT_STREAM_IN_PROGRESS = 2
            const val PROCESS_INPUT_STREAM_SUCCESS = 3
        }
    }

    /**
     * Indicates that the callback handler needs to update its appearance or information based on
     * the result of the task. Expected to be called from the main thread.
     */
    fun updateFromDownload(result: String)

    /**
     * Indicate to callback handler any progress update.
     * @param progressCode must be one of the constants defined in DownloadCallback.Progress.
     * @param percentComplete must be 0-100.
     */
    fun onProgressUpdate(progressCode: Int, percentComplete: Int)

    /**
     * Indicates that the download operation has finished. This method is called even if the
     * download hasn't completed successfully.
     */
    fun finishDownloading()

}
