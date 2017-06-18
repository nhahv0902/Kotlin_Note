package com.nhahv.note.screen.notecreation.preview

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
        abstract fun onBackPressed()
        abstract fun onClickDeleteImage(position: Int)
    }

    /**
     * Presenter.
     */
    interface Presenter : BasePresenter
}
