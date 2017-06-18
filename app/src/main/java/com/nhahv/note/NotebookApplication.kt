package com.nhahv.note

import android.support.multidex.MultiDexApplication
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */
class NotebookApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}
