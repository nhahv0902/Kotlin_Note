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
    val url = "http://api.openweathermap.org/data/2.5/weather?lat=10.778182&lon=106.665504&lang=vi&appid=b84739739d2e8be63e5c449816079277"


    async() {
      val text = URL(url).readText()
      Log.d("TAG", text)
    }

  }
}
