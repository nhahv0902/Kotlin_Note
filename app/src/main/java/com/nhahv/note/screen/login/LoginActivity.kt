package com.nhahv.note.screen.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityLoginBinding
import com.nhahv.note.screen.BaseActivity

class LoginActivity : BaseActivity() {

    private var mViewModel: LoginContract.ViewModel? = null
    private var mBinding: ActivityLoginBinding? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = LoginViewModel(this)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mBinding?.viewModel = mViewModel as LoginViewModel

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window?.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel?.onActivityResult(requestCode, resultCode, data)
    }

    fun visibleLoginScreen() {
        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.translate_view_login)
        mBinding?.let {
            it.textSplashScreen.startAnimation(animation)
            it.frameGoogle.startAnimation(animation)
            it.frameFacebook.startAnimation(animation)
        }
    }
}
