package com.nhahv.note.screen.notecreation.preview

/**
 * Listens to user actions from the UI ([NotePreviewActivity]), retrieves the data and updates
 * the UI as required.
 */
class NotePreviewPresenter(
    private val mViewModel: NotePreviewContract.ViewModel) : NotePreviewContract.Presenter {

    override fun onStart() {}

    override fun onStop() {}

    companion object {
        private val TAG = NotePreviewPresenter::class.java.name
    }
}
