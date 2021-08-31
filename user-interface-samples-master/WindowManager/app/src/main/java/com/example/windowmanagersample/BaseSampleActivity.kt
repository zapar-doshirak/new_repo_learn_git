/*
 *
 *  * Copyright 2020 The Android Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.windowmanagersample

import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.windowmanagersample.backend.MidScreenFoldBackend
import java.util.concurrent.Executor

/**
 * Base class for Activities in the samples that allows specifying the [WindowBackend]
 * that should be used by the sample activities in this package. This allows switching between the
 * default backend provided on the device and a test backend (e.g. if the device doesn't provide
 * any).
 */
abstract class BaseSampleActivity : AppCompatActivity() {
    companion object {
        const val BACKEND_TYPE_EXTRA = "backend_type"

        const val BACKEND_TYPE_DEVICE_DEFAULT = 0
        const val BACKEND_TYPE_MID_SCREEN_FOLD = 1
    }

    private val handler = Handler(Looper.getMainLooper())
    val mainThreadExecutor = Executor { r: Runnable -> handler.post(r) }

    /**
     * Method to get our test backend if we are not using the device default
     * Returning the specific Class and not the [WindowBackend] Interface to save
     * on casting code later to access specific testing methods.
     *
     * @return [MidScreenFoldBackend]
     */
    fun getTestBackend(): MidScreenFoldBackend? {
        return when (intent.getIntExtra(BACKEND_TYPE_EXTRA, BACKEND_TYPE_DEVICE_DEFAULT)) {
            BACKEND_TYPE_MID_SCREEN_FOLD -> MidScreenFoldBackend()
            else -> null
        }
    }
}