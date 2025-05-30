/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(), Configuration.Provider {
  override fun onCreate(){
    super.onCreate()
    Log.d("Sunflower", "App started!")

    //cần gọi cherypick
    val result = computeAdvanced(5) // cần cherry-pick computeBase + computeAdvanced
    Log.d("StartupLog", "Computed value: $result")

  }
  override val workManagerConfiguration: Configuration
    get() = Configuration.Builder()
      .setMinimumLoggingLevel(if (BuildConfig.DEBUG) android.util.Log.DEBUG else android.util.Log.ERROR)
      .build()

  // tạo 2 hàm commit cần thiết để cherry pick
  fun computeBase(value : Int) : Int {
    return value*2
  }
  //hàm B phụ thuộc, cũng cần để chery pick
  fun computeAdvanced(value :Int ):Int{
    return computeBase(value) +10
  }
}
