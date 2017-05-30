package com.nhahv.note.screen.notecreation

import android.content.Intent
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.creation.NotebookDataSource
import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
interface NoteCreationContract {
    abstract class ViewModel(activity: NoteCreationActivity) : BaseViewModel<Presenter>(activity) {
        abstract fun onPickPicture()
        abstract fun onPreviewImage()
        abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }

    interface Presenter : BasePresenter {
        fun addNotebook(notebook: Notebook, callback: NotebookDataSource.Callback)
    }
}