package com.nhahv.note.screen.splashscreen

/**
 * Listens to user actions from the UI ([SplashScreenActivity]), retrieves the data and
 * updates
 * the UI as required.
 */
class SplashScreenPresenter(
        private val mViewModel: SplashScreenContract.ViewModel) : SplashScreenContract.Presenter {

    override fun onStart() {}

    override fun onStop() {}

    companion object {
        private val TAG = SplashScreenPresenter::class.java.name
    }
}
