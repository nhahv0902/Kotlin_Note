package com.nhahv.note.screen.loadpicture.imagepicker

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityImagePickerBinding
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.loadpicture.model.Folder
import com.nhahv.note.util.BundleConstant.BUNDLE_FOLDERS

class ImagePickerActivity : BaseActivity() {

    private var mViewModel: ImagePickerViewModel? = null
    private var mFolder: Folder? = null

    companion object {
        fun newIntent(context: Context, folder: Folder): Intent {
            val intent = Intent(context, ImagePickerActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_FOLDERS, folder)
            intent.putExtras(bundle)
            return intent
        }
    }

    private fun getDataFromIntent() {
        val bundle = intent.extras
        bundle?.let {
            mFolder = bundle.getParcelable(BUNDLE_FOLDERS)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromIntent()
        mViewModel = ImagePickerViewModel(this, mFolder)
        val binding: ActivityImagePickerBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_image_picker)
        binding.viewModel = mViewModel
    }
}
