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

package com.example.android.networkconnect

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.networkconnect.datafragment.DataFragment
import com.example.android.networkconnect.datafragment.datainj.DaggerApiComponent

/**
 * Sample Activity demonstrating how to connect to the network and fetch raw
 * HTML. It uses a Fragment that encapsulates the network operations on an AsyncTask.
 *
 * This sample uses a TextView to display output.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        DaggerApiComponent.create().inject(this)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_act_container, DataFragment.newInstance())
                    .commitNow()
        }
    }
}
