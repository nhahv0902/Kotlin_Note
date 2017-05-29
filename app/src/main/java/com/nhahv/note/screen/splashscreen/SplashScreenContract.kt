package com.nhahv.note.screen.splashscreen

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * This specifies the contract between the view and the presenter.
 */
interface SplashScreenContract {
    /**
     * View.
     */
    abstract class ViewModel(activity: SplashScreenActivity) : BaseViewModel<Presenter>(activity)

    /**
     * Presenter.
     */
    interface Presenter : BasePresenter
}
