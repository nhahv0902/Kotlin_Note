package com.nhahv.note.ui.notebook

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.nhahv.note.R
import com.nhahv.note.databinding.FragmentNotebookBinding

/**
 * A simple [Fragment] subclass.
 */
class NotebookFragment : Fragment() {

  companion object {
    fun newInstance(): NotebookFragment {
      return NotebookFragment()
    }
  }

  var mViewModel: NotebookViewModel? = null

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    mViewModel = NotebookViewModel(activity as AppCompatActivity)
    val binding = FragmentNotebookBinding.inflate(inflater, container, false)
    binding.viewModel = mViewModel
    return binding.root
  }
}
