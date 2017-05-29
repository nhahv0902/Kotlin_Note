package com.nhahv.note.screen.main

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface MainContract {

    abstract class ViewModel(activity: MainActivity) : BaseViewModel<Presenter>(activity)

    interface Presenter : BasePresenter
}