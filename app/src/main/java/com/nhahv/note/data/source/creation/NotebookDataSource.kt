package com.nhahv.note.data.source.creation

import com.nhahv.note.data.model.Notebook

/**
 * Created by Nhahv0902 on 5/31/2017.
 * <>
 */
interface NotebookDataSource {
    fun addNotebook(notebook: Notebook, callback: Callback)

    fun getNotebooks(callback: LoadNotebookCallback)

    interface Callback {

        fun onSuccess()

        fun onError()
    }

    interface LoadNotebookCallback {

        fun onNotebooksLoaded(notebooks: ArrayList<Notebook>)

        fun onDataNotAvailable()
    }
}