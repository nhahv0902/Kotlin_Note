package com.nhahv.note.screen.plash

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityPlashScreenBinding
import com.nhahv.note.screen.BaseActivity

class PlashScreenActivity : BaseActivity() {

    private var mViewModel: PlashScreenViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	val binding: ActivityPlashScreenBinding = DataBindingUtil.setContentView(this,
	    R.layout.activity_plash_screen)
	mViewModel = PlashScreenViewModel(this)
	binding.viewModel = mViewModel
    }
}
