package com.nhahv.note.screen.notebook

import android.content.Context
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.util.toast

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NotebookViewModel(activity: BaseActivity) : NotebookContract.ViewModel(activity) {

    private var mPresenter: NotebookContract.Presenter? = null
    private val mContext: Context? = activity.applicationContext

    @get: Bindable
    var mAdapter: NotebookAdapter? = null
        set(adapter) {
            field = adapter
            notifyPropertyChanged(BR.mAdapter)
        }
    @get: Bindable
    var mIsRefresh: Boolean = false
        set(adapter) {
            field = adapter
            notifyPropertyChanged(BR.mIsRefresh)
        }
    var mNotebooks: ArrayList<Notebook> = ArrayList()

    init {
        mAdapter = NotebookAdapter(this, mNotebooks)
    }

    override fun onStart() {}

    override fun onStop() {}

    override fun setPresenter(presenter: NotebookContract.Presenter) {
        mPresenter = presenter
    }


    override fun onGetNotebooksSuccess(notebooks: ArrayList<Notebook>) {
        mNotebooks.clear()
        mNotebooks.addAll(notebooks)
        mAdapter?.notifyDataSetChanged()
        mIsRefresh = false
    }

    override fun onGetNotebooksError() {
        mContext?.toast(mContext, R.string.msg_load_data_error)
    }

    override fun onLoadNotebookData() {
        mPresenter?.loadNotebookData()
    }
}

