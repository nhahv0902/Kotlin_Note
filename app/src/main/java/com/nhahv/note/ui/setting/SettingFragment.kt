package com.nhahv.note.ui.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.note.databinding.FragmentSettingBinding

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

  companion object {
    fun newInstance(): SettingFragment {
      return SettingFragment()
    }
  }

  var mViewModel: SettingViewModel? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    mViewModel = SettingViewModel(activity as AppCompatActivity)
    val binding: FragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container, false)
    binding.viewModel = mViewModel
    return binding.root
  }
}
