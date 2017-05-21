package com.nhahv.note.ui.main

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
}
