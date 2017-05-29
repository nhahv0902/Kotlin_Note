package com.nhahv.note.screen.loadpicture.folder

import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.screen.loadpicture.imagepicker.ImagePickerActivity
import com.nhahv.note.screen.loadpicture.model.Folder

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <
 */

class AlbumViewModel(activity: AlbumActivity) : AlbumContract.ViewModel(activity) {

    private var mPresenter: AlbumContract.Presenter? = null

    override fun onStart() {
        mPresenter?.onStart()
    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: AlbumContract.Presenter) {
        mPresenter = presenter
    }

    private var mFolders: ArrayList<Folder> = ArrayList()
    private val mContext = activity.applicationContext

    @get: Bindable
    var mAdapter: AlbumAdapter? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
        }

    init {
//        val loader = LoaderPicture(mContext)
        mAdapter = AlbumAdapter(this, mFolders)


    }

    fun onStartImagePicker(folder: Folder) {
        mActivity.startActivity(ImagePickerActivity.newIntent(mContext, folder))
    }

    fun onOpenCamera() {

    }

    fun updateAdapter(folders: ArrayList<Folder>) {
        mFolders.addAll(folders)
        mAdapter?.notifyDataSetChanged()
    }
}
