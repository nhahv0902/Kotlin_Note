package com.nhahv.note.screen.previewpicture

import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR

/**
 * Exposes the data to be used in the PreviewPicture screen.
 */

class PreviewPictureViewModel(activity: PreviewPictureActivity,
                              images: ArrayList<String>?,
                              position: Int) : PreviewPictureContract.ViewModel(
        activity) {

    private var mPresenter: PreviewPictureContract.Presenter? = null
    private val mImages: ArrayList<String>? = images
    val mPosition = position

    @get: Bindable
    var mAdapter: ViewPagerAdapter? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
        }

    init {
        mAdapter = ViewPagerAdapter(mImages)
    }

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
