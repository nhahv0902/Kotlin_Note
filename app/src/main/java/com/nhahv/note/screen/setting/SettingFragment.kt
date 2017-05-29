package com.nhahv.note.screen.setting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.note.databinding.FragmentSettingBinding
import com.nhahv.note.screen.BaseActivity

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
	mViewModel = SettingViewModel(activity as BaseActivity, this)
	val binding: FragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container,
	    false)
	binding.viewModel = mViewModel
	return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
	super.onActivityResult(requestCode, resultCode, data)
	mViewModel?.onActivityResult(requestCode, resultCode, data)
    }
}
