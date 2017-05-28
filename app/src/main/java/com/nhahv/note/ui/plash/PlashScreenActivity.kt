package com.nhahv.note.ui.plash

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityPlashScreenBinding
import com.nhahv.note.ui.BaseActivity
import org.jetbrains.anko.custom.async
import java.net.URL

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
