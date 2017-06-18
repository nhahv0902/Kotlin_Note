package com.nhahv.note.screen.notecreation

import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.creation.NotebookDataSource
import com.nhahv.note.data.source.creation.NotebookRepository
import com.nhahv.note.data.source.picture.PictureStorageRepository

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
class NoteCreationPresenter(
        viewModel: NoteCreationContract.ViewModel) : NoteCreationContract.Presenter {

    val mViewModel: NoteCreationContract.ViewModel = viewModel
    val mNotebookRepository = NotebookRepository()
    private val mUploadRepository = PictureStorageRepository()


    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun addNotebook(notebook: Notebook, callback: NotebookDataSource.Callback) {
        mNotebookRepository.addNotebook(notebook, callback)
    }
}