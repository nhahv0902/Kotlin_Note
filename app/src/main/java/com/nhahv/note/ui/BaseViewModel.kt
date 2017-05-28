package com.nhahv.note.ui

import android.databinding.BaseObservable
import android.support.v7.app.AppCompatActivity
import com.nhahv.note.ui.main.MainActivity

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <.
 */
open class BaseViewModel(activity: BaseActivity) : BaseObservable() {
    var mActivity: BaseActivity = activity
}