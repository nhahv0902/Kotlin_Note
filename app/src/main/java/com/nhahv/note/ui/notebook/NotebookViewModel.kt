package com.nhahv.note.ui.notebook

import android.databinding.Bindable
import android.databinding.ObservableField
import android.support.v7.app.AppCompatActivity
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.ui.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NotebookViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {

  @get: Bindable
  var mAdapter: NotebookAdapter? = null
    set(adapter) {
      field = adapter
      notifyPropertyChanged(BR.mAdapter)
    }
  var mNotebooks: ArrayList<Notebook> = ArrayList<Notebook>()

  init {
    mNotebooks.add(Notebook())
    mNotebooks.add(Notebook())
    mNotebooks.add(Notebook())
    mNotebooks.add(Notebook())
    mNotebooks.add(Notebook())
    mAdapter = NotebookAdapter(this, mNotebooks)
  }


}

