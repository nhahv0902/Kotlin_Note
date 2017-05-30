package com.nhahv.note.screen.notebook

import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.creation.NotebookDataSource
import com.nhahv.note.data.source.creation.NotebookRepository

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
class NotebookPresenter(viewModel: NotebookContract.ViewModel) : NotebookContract.Presenter {
    val mViewModel = viewModel
    val mRepository = NotebookRepository()

    override fun onStart() {

    }

    override fun onStop() {
    }

    init {
        loadNotebookData()
    }

    override fun loadNotebookData() {
        mRepository.getNotebooks(object : NotebookDataSource.LoadNotebookCallback {
            override fun onNotebooksLoaded(notebooks: ArrayList<Notebook>) {
                mViewModel.onGetNotebooksSuccess(notebooks)
            }

            override fun onDataNotAvailable() {
                mViewModel.onGetNotebooksError()
            }
        })
    }
}