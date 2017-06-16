package com.nhahv.note.screen.notecreation.preview

import android.content.Intent
import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * This specifies the contract between the view and the presenter.
 */
interface NotePreviewContract {
    /**
     * View.
     */
    abstract class ViewModel(activity: NotePreviewActivity) : BaseViewModel<Presenter>(activity) {
        abstract fun onClickImage(position: Int, image: String)
        abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
        abstract fun onBackPressed()
    }

    /**
     * Presenter.
     */
    interface Presenter : BasePresenter
}
