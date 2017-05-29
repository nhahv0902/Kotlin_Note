package com.nhahv.note.screen.previewpicture

/**
 * Exposes the data to be used in the PreviewPicture screen.
 */

class PreviewPictureViewModel(activity: PreviewPictureActivity) : PreviewPictureContract.ViewModel(
        activity) {

    private var mPresenter: PreviewPictureContract.Presenter? = null

    override fun onStart() {
        mPresenter?.onStart()

    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: PreviewPictureContract.Presenter) {
        mPresenter = presenter
    }

}
