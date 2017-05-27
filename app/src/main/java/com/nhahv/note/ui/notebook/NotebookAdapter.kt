package com.nhahv.note.ui.notebook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.databinding.ItemNotebookBinding
import com.nhahv.note.ui.notebook.NotebookAdapter.NotebookHolder

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>
 */

class NotebookAdapter(viewModel: NotebookViewModel,
    notebooks: List<Notebook>?) : RecyclerView.Adapter<NotebookHolder>() {

  var mInflater: LayoutInflater? = null
  val mNotebooks = notebooks
  val mNotebookViewModel = viewModel

  override fun onBindViewHolder(holder: NotebookHolder?, position: Int) {
    val notebook = mNotebooks?.get(position)
    notebook?.let { holder?.bind(notebook, position) }
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup?, position: Int): NotebookHolder {
    if (mInflater == null) {
      mInflater = LayoutInflater.from(viewGroup?.context)
    }
    val binding: ItemNotebookBinding = ItemNotebookBinding.inflate(mInflater, viewGroup, false)
    binding.viewModel = mNotebookViewModel
    return NotebookHolder(binding)
  }

  override fun getItemCount(): Int {
    return mNotebooks?.size ?: 0
  }

  class NotebookHolder(binding: ItemNotebookBinding) : RecyclerView.ViewHolder(binding.root) {
    val mBinding = binding
    fun bind(notebook: Notebook, position: Int) {
      mBinding.notebook = notebook
      mBinding.position = position
      mBinding.executePendingBindings()
    }
  }
}
