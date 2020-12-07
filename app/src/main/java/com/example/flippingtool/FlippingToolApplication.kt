package com.example.flippingtool

import android.app.Application
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute

/**
 * @author Dzemal at 3.12.2020.
 **/
class FlippingToolApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCenter.start(this, BuildConfig.APPCENTER_SECRET,
                Analytics::class.java, Crashes::class.java, Distribute::class.java)
    }
}