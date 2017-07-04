package com.nhahv.note.screen.notecreation.preview

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.databinding.Bindable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.previewpicture.PreviewPictureActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES

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
        mActivity.startActivity(
            mImages?.let {
                PreviewPictureActivity.newIntent(mActivity.applicationContext, it, position)
            })
    }

    override fun onClickDeleteImage(position: Int) {
        mImages?.let {
            mImages?.removeAt(position)
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        if (mSize != mImages?.size) {
            AlertDialog.Builder(mActivity)
                .setMessage(R.string.msg_delete_image)
                .setPositiveButton(R.string.action_agree) { dialog, _ ->
                    mImages?.let {
                        val intent = Intent()
                        val bundle = Bundle()
                        bundle.putStringArrayList(BUNDLE_IMAGES, mImages)
                        intent.putExtras(bundle)
                        mActivity.setResult(RESULT_OK, intent)
                        dialog.dismiss()
                        mActivity.finish()
                    }
                }
                .setNegativeButton(R.string.action_disagree) { dialog, _ ->
                    dialog.dismiss()
                    mActivity.finish()
                }
                .show()
        } else {
            mActivity.finish()
        }
    }
}
