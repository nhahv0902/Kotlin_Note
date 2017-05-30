package com.nhahv.note.data.source.creation

import com.nhahv.note.data.model.Notebook

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */

class NotebookRepository : NotebookDataSource {

    private val mDataSource: NotebookRemoteDataSource = NotebookRemoteDataSource()

    override fun addNotebook(notebook: Notebook, callback: NotebookDataSource.Callback) {
        mDataSource.addNotebook(notebook, callback)
    }

    override fun getNotebooks(callback: NotebookDataSource.LoadNotebookCallback) {
        mDataSource.getNotebooks(callback)
    }
}
