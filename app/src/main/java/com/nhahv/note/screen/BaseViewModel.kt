package com.nhahv.note.screen

import android.databinding.BaseObservable

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <.
 */
open class BaseViewModel(activity: BaseActivity) : BaseObservable() {
    var mActivity: BaseActivity = activity
}