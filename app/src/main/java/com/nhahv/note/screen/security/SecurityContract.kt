package com.nhahv.note.screen.security

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface SecurityContract {

    abstract class ViewModel(activity: SecurityActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}