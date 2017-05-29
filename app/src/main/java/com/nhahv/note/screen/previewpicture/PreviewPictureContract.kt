package com.nhahv.note.screen.previewpicture

import com.nhahv.note.screen.BasePresenter
import com.nhahv.note.screen.BaseViewModel

/**
 * This specifies the contract between the view and the presenter.
 */
interface PreviewPictureContract {
    /**
     * View.
     */
    abstract class ViewModel(activity: PreviewPictureActivity) : BaseViewModel<Presenter>(activity)

    /**
     * Presenter.
     */
    interface Presenter : BasePresenter
}
