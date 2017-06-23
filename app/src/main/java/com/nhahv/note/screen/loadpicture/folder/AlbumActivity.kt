package com.nhahv.note.screen.loadpicture.folder

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityFolderBinding
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.loadpicture.model.LoaderPicture
import com.nhahv.note.util.mHashPermission
import com.nhahv.note.util.readStoragePermission
import com.nhahv.note.util.toast

class AlbumActivity : BaseActivity() {

    private var mViewModel: AlbumViewModel? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, AlbumActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = AlbumViewModel(this)
        val presenter = AlbumPresenter(mViewModel as AlbumContract.ViewModel)
        mViewModel?.setPresenter(presenter)

        val binding: ActivityFolderBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_folder)
        binding.viewModel = mViewModel

        if (readStoragePermission(applicationContext,
                R.string.msg_permission_request_read_storage_external, this)) {
            onLoadImageFromSDCard()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        mViewModel?.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        mViewModel?.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel?.onActivityResult(requestCode, resultCode, data)
    }

    fun onLoadImageFromSDCard() {
        val loader = LoaderPicture(this)
        mViewModel?.updateAdapter(loader.loadPictures())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            mHashPermission[Manifest.permission.READ_EXTERNAL_STORAGE] -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onLoadImageFromSDCard()
                } else {
                    toast(this.applicationContext, R.string.msg_denied_read_storage_external)
                }
                return
            }
            mHashPermission[Manifest.permission.CAMERA], mHashPermission[Manifest.permission.WRITE_EXTERNAL_STORAGE] -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mViewModel?.openCamera()
                } else {
                    toast(this.applicationContext, R.string.msg_denied_camera)
                }
                return
            }
        }
    }
}
