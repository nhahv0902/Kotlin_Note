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
import com.nhahv.note.util.permission.mHashPermission
import com.nhahv.note.util.permission.readStoragePermission
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
            onLoadImageFromSDcard()
        }


    }

    fun onLoadImageFromSDcard() {
        val loader = LoaderPicture(this)
        mViewModel?.updateAdapter(loader.loadPictures())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            mHashPermission[Manifest.permission.READ_EXTERNAL_STORAGE] -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onLoadImageFromSDcard()
                } else {
                    toast(this.applicationContext, R.string.msg_denied_read_storage_external)
                }
                return
            }
        }
    }
}
