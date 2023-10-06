package com.qbill.app_config

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class AppConfig : Application() {
    var context = this

    companion object {

    }

    override fun onCreate() {
        super.onCreate()
        setMode()
        showPortraitMode()
    }

    /**
     * for setup ui mode
     */
    private fun setMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    /**
     * for showing portrait mode
     */
    private fun showPortraitMode() {
        registerActivityLifecycleCallbacks(
            object : ActivityLifecycleCallbacks {

                @SuppressLint("SourceLockedOrientationActivity")
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


                }

                override fun onActivityStarted(activity: Activity) {}

                override fun onActivityResumed(activity: Activity) {}

                override fun onActivityPaused(activity: Activity) {}

                override fun onActivityStopped(activity: Activity) {}

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

                override fun onActivityDestroyed(activity: Activity) {}
            })
    }


}