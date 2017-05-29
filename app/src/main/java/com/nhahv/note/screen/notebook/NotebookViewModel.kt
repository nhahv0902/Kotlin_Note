package com.nhahv.note.screen.notebook

import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NotebookViewModel(activity: BaseActivity) : NotebookContract.ViewModel(activity) {

    private var mPresenter: NotebookContract.Presenter? = null

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

    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun setPresenter(presenter: NotebookContract.Presenter) {
        mPresenter = presenter
    }
}

