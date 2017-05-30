package com.nhahv.note.screen.splashscreen

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivitySplashScreenBinding
import com.nhahv.note.screen.BaseActivity


/**
 * SplashScreen Screen.
 */
class SplashScreenActivity : BaseActivity() {

    private var mViewModel: SplashScreenContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = SplashScreenViewModel(this)

        val presenter = SplashScreenPresenter(mViewModel as SplashScreenViewModel)
        mViewModel?.setPresenter(presenter)

        val binding: ActivitySplashScreenBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_splash_screen)
        binding.viewModel = mViewModel as SplashScreenViewModel?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

    }

    override fun onStart() {
        super.onStart()
        mViewModel?.onStart()
    }

    override fun onStop() {
        mViewModel?.onStop()
        super.onStop()
    }
}
