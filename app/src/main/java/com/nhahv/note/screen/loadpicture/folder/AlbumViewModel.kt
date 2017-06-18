package com.nhahv.note.screen.loadpicture.folder

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.Bindable
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.loadpicture.imagepicker.ImagePickerActivity
import com.nhahv.note.screen.loadpicture.model.Folder
import com.nhahv.note.util.*
import com.nhahv.note.util.BundleConstant.BUNDLE_FILE_NAME
import com.nhahv.note.util.BundleConstant.BUNDLE_FILE_URI
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES
import com.nhahv.note.util.DataUtil.JPEG_FILE_PREFIX
import com.nhahv.note.util.DataUtil.JPEG_FILE_SUFFIX
import com.nhahv.note.util.Request.REQUEST_PICK_IMAGE
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.nhahv.note.BuildConfig
import com.nhahv.note.screen.main.MainActivity


/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <
 */

class AlbumViewModel(activity: AlbumActivity) : AlbumContract.ViewModel(activity) {

    private var mPresenter: AlbumContract.Presenter? = null
    private var mCurrentPhotoPath: String? = null
    private var mFileUri: Uri? = null

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

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(BUNDLE_FILE_URI, mFileUri)
        outState?.putString(BUNDLE_FILE_NAME, mCurrentPhotoPath)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        mFileUri = savedInstanceState?.getParcelable(BUNDLE_FILE_URI)
        mCurrentPhotoPath = savedInstanceState?.getString(BUNDLE_FILE_NAME)

    }

    fun onStartImagePicker(folder: Folder) {
        mActivity.startActivityForResult(ImagePickerActivity.newIntent(mContext, folder),
                REQUEST_PICK_IMAGE)
    }

    fun onOpenCamera() {
        if (checkCameraHardware()) {
            if (cameraPermission(mContext, R.string.msg_permission_request_camera,
                    mActivity) && writeStoragePermission(mContext,
                    R.string.msg_permission_request_read_storage_external, mActivity)) {
                openCamera()
            }
        } else {
            mContext.toast(mContext, R.string.msg_device_not_support_camera)
        }
    }

    override fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(mContext.packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                return
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                var photoURI: Uri = Uri.fromFile(createImageFile())

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoURI = FileProvider.getUriForFile(mContext, "com.nhahv.note",
                            createImageFile())
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                mActivity.startActivityForResult(intent, REQUEST_CAMERA)
            }
        }
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
            REQUEST_CAMERA -> {
                handleBigCameraPhoto()
            }
        }
    }

    private fun getAlbumName(): String {
        return mContext.getString(R.string.text_album_name)
    }

    private fun getAlbumDir(): File? {
        var storageDir: File? = null
        if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            storageDir = File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    getAlbumName())

            if (!storageDir.mkdirs()) {
                if (!storageDir.exists()) {
                    Log.d("CameraSample", "failed to create directory");
                    return null
                }
            }
        } else {
            Log.v(mContext.getString(R.string.app_name),
                    "External storage is not mounted READ/WRITE.")
        }
        return storageDir
    }

    private fun createImageFile(): File? {
        try {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val imageFileName = JPEG_FILE_PREFIX + timeStamp + "_"
            val albumF = getAlbumDir()
            val imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF)
            mCurrentPhotoPath = imageF.absolutePath
            return imageF
        } catch (exception: IOException) {
            return null
        }
    }

    private fun galleryAddPic() {
        val mediaScanIntent = Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE")
        val f = File(mCurrentPhotoPath)
        val contentUri = Uri.fromFile(f)
        mediaScanIntent.data = contentUri
        mActivity.sendBroadcast(mediaScanIntent)
    }

    fun getOutputMediaFileUri(): Uri {
        return Uri.fromFile(createImageFile())
    }

    private fun handleBigCameraPhoto() {
        if (mCurrentPhotoPath != null) {
            galleryAddPic()
            val images: ArrayList<String> = ArrayList()
            images.add(mCurrentPhotoPath!!)
            val intent = Intent()
            val bundle = Bundle()
            bundle.putStringArrayList(BUNDLE_IMAGES, images)
            intent.putExtras(bundle)
            mActivity.setResult(RESULT_OK, intent)
            mCurrentPhotoPath = null
            mActivity.finish()
        } else {
            mContext.log("error file  $mCurrentPhotoPath")
        }
    }
}
