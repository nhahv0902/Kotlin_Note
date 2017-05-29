package com.nhahv.note.screen.login

import android.content.Intent
import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface LoginContract {

    abstract class ViewModel(activity: LoginActivity) : BaseViewModel<Presenter>(activity) {
        abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface Presenter : BasePresenter
}