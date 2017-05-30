package com.nhahv.note.screen.previewpicture

/**
 * Listens to user actions from the UI ([PreviewPictureActivity]), retrieves the data and
 * updates
 * the UI as required.
 */
class PreviewPicturePresenter(
        viewModel: PreviewPictureContract.ViewModel) : PreviewPictureContract.Presenter {

    private var mViewModel = viewModel

    override fun onStart() {
    }

    override fun onStop() {
    }

    companion object {
        private val TAG = PreviewPicturePresenter::class.java.name
    }
}
