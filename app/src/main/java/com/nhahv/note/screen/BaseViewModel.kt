package com.nhahv.note.screen

import android.databinding.BaseObservable

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <.
 */
abstract class BaseViewModel<in T : BasePresenter>(activity: BaseActivity) : BaseObservable() {
    var mActivity: BaseActivity = activity
    abstract fun onStart()

    abstract fun onStop()

    abstract fun setPresenter(presenter: T)
}