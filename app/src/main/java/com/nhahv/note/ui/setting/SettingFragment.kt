package com.nhahv.note.ui.setting

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhahv.note.R

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {

  companion object {
    fun newInstance(): SettingFragment {
      return SettingFragment()
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater!!.inflate(R.layout.fragment_notebook, container, false)
  }
}
