package com.nhahv.note.ui.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

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
}
