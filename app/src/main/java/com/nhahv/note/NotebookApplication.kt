package com.nhahv.note

import android.app.Application
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */
class NotebookApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}
