package com.nhahv.note.screen.loadpicture.imagepicker

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.os.Bundle
import android.text.TextUtils
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.loadpicture.model.Folder
import com.nhahv.note.screen.previewpicture.PreviewPictureActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ImagePickerViewModel(activity: ImagePickerActivity,
        folder: Folder?) : ImagePickerContract.ViewModel(
        activity) {

    private val mContext: Context = activity.applicationContext
    private val mFolder: Folder? = folder
    private var mPresenter: ImagePickerContract.Presenter? = null

    @get: Bindable
    var mTitle: String? = mContext.getString(R.string.title_albums)
        set(value) {
            field = value
            notifyPropertyChanged(BR.mTitle)
        }

    @get: Bindable
    var mAdapter: ImagePickerAdapter? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
        }

    @get: Bindable
    var mNumberImage: Int? = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.mNumberImage)
        }

    var mImagePicks: ObservableArrayList<String> = ObservableArrayList()

    init {
        mTitle = mFolder?.name
        mAdapter = ImagePickerAdapter(this, mFolder?.images)
    }

    override fun onStart() {
        mPresenter?.onStart()
    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: ImagePickerContract.Presenter) {
        mPresenter = presenter
    }

    fun onImagePicker(position: Int) {
        mActivity.startActivity(
                mFolder?.images?.let { PreviewPictureActivity.newIntent(mContext, it, position) })
    }

    fun onDonePickImage() {
        val intent = Intent()
        val bundle = Bundle()
        bundle.putStringArrayList(BUNDLE_IMAGES, mImagePicks)
        intent.putExtras(bundle)
        mActivity.setResult(RESULT_OK, intent)
        mActivity.finish()
    }

    fun onCheckedPickImage(checked: Boolean, imagePick: String, position: Int) {
        mNumberImage = mNumberImage?.plus((if (checked) 1 else -1))
        if (checked) {
            mImagePicks.add(imagePick)
        } else {
            mImagePicks
                    .filter { TextUtils.equals(it, imagePick) }
                    .forEach { mImagePicks.remove(it) }
        }
    }
}
