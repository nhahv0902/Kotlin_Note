package com.nhahv.note.ui.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityMainBinding
import com.nhahv.note.ui.BaseActivity

class MainActivity : BaseActivity() {

  private var mViewModel: MainViewModel? = null


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    mViewModel = MainViewModel(this)
    binding.viewModel = mViewModel
  }


  companion object {
    fun newIntent(context: Context): Intent {
      val intent: Intent = Intent(context, MainActivity::class.java)
      return intent
    }
  }
}
