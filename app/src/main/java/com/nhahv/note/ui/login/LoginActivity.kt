package com.nhahv.note.ui.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityLoginBinding
import com.nhahv.note.ui.BaseActivity

class LoginActivity : BaseActivity() {

    private var mViewModel: LoginViewModel? = null

    companion object {
	fun newIntent(context: Context): Intent {
	    return Intent(context, LoginActivity::class.java)
	}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this,
	    R.layout.activity_login)
	mViewModel = LoginViewModel(this)
	binding.viewModel = mViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
	super.onActivityResult(requestCode, resultCode, data)
	mViewModel?.onActivityResult(requestCode, resultCode, data)
    }
}
