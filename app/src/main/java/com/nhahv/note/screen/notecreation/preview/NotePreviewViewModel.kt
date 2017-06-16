package com.nhahv.note.screen.notecreation.preview

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.databinding.Bindable
import android.os.Bundle
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.screen.previewpicture.PreviewPictureActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES
import com.nhahv.note.util.Request.REQUEST_NOTE_PREVIEW

/**
 * Exposes the data to be used in the NotePreview screen.
 */

class NotePreviewViewModel(activity: NotePreviewActivity,
        images: ArrayList<String>?) : NotePreviewContract.ViewModel(
        activity) {

    private var mPresenter: NotePreviewContract.Presenter? = null

    @get : Bindable
    var mAdapter: GridNotePreviewAdapter? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
        }

    @get: Bindable
    var mImages: ArrayList<String>? = images
        set(value) {
            field = value
            notifyPropertyChanged(BR.mImages)
        }
    private val mSize = images?.size

    override fun onStart() {
        mPresenter?.onStart()
    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: NotePreviewContract.Presenter) {
        mPresenter = presenter
    }

    init {
        mAdapter = GridNotePreviewAdapter(this, mImages)
    }

    override fun onClickImage(position: Int, image: String) {
        mActivity.startActivityForResult(
                mImages?.let {
                    PreviewPictureActivity.newIntent(mActivity.applicationContext, it, position)
                }, REQUEST_NOTE_PREVIEW)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_NOTE_PREVIEW) {
            data?.let {
                mImages?.clear()
                mImages?.addAll(data.extras.getStringArrayList(BUNDLE_IMAGES))
                mAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onBackPressed() {
        if (mSize != mImages?.size) {
            mImages?.let {
                val intent = Intent()
                val bundle = Bundle()
                bundle.putStringArrayList(BUNDLE_IMAGES, mImages)
                intent.putExtras(bundle)
                mActivity.setResult(RESULT_OK, intent)
                mActivity.finish()
            }
        }
    }
}
