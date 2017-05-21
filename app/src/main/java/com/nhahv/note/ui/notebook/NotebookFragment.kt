package com.nhahv.note.ui.notebook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhahv.note.R

/**
 * A simple [Fragment] subclass.
 */
class NotebookFragment : Fragment() {

  companion object {
    fun newInstance(): NotebookFragment {
      return NotebookFragment()
    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    return inflater!!.inflate(R.layout.fragment_notebook, container, false)
  }
}
