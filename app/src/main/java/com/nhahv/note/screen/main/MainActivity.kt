package com.nhahv.note.screen.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityMainBinding
import com.nhahv.note.screen.BaseActivity

class MainActivity : BaseActivity() {

    private var mViewModel: MainContract.ViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = MainViewModel(this)
        val presenter: MainContract.Presenter = MainPresenter(mViewModel as MainViewModel)
        mViewModel?.setPresenter(presenter)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main)
        binding.viewModel = mViewModel as MainViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel?.onActivityResult(requestCode, resultCode, data)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent: Intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }
}
