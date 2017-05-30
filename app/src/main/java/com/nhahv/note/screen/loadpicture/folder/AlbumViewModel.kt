package com.nhahv.note.screen.loadpicture.folder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.Bindable
import android.provider.MediaStore
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.loadpicture.imagepicker.ImagePickerActivity
import com.nhahv.note.screen.loadpicture.model.Folder
import com.nhahv.note.util.Request.REQUEST_PICK_IMAGE
import com.nhahv.note.util.cameraPermission
import com.nhahv.note.util.toast


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
        mAdapter = AlbumAdapter(this, mFolders)
    }

    fun onStartImagePicker(folder: Folder) {
        mActivity.startActivityForResult(ImagePickerActivity.newIntent(mContext, folder),
                REQUEST_PICK_IMAGE)
    }

    fun onOpenCamera() {
        if (checkCameraHardware()) {
            if (cameraPermission(mContext, R.string.msg_permission_request_camera, mActivity)) {
                openCamera()
            }
        } else {
            mContext.toast(mContext, R.string.msg_device_not_support_camera)
        }
    }

    override fun openCamera() {
        mActivity.startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }

    private fun checkCameraHardware(): Boolean {
        return mContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
    }

    fun updateAdapter(folders: ArrayList<Folder>) {
        mFolders.addAll(folders)
        mAdapter?.notifyDataSetChanged()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) return
        when (requestCode) {
            REQUEST_PICK_IMAGE -> {
                mActivity.setResult(RESULT_OK, data)
                mActivity.finish()
            }

        }
    }
}
