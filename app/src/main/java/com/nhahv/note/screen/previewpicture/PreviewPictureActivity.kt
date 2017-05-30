package com.nhahv.note.screen.previewpicture

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityPreviewPictureBinding
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES
import com.nhahv.note.util.BundleConstant.BUNDLE_POSITION

/**
 * PreviewPicture Screen.
 */
class PreviewPictureActivity : BaseActivity() {

    private var mViewModel: PreviewPictureContract.ViewModel? = null
    private var mImages: ArrayList<String>? = null
    private var mPosition: Int = 0


    companion object {
        fun newIntent(context: Context, images: ArrayList<String>, position: Int): Intent {
            val intent = Intent(context, PreviewPictureActivity::class.java)
            val bundle = Bundle()
            bundle.putStringArrayList(BUNDLE_IMAGES, images)
            bundle.putInt(BUNDLE_POSITION, position)
            intent.putExtras(bundle)
            return intent
        }
    }

    private fun getDataFromIntent() {
        val bundle = intent.extras
        bundle?.let {
            mImages = bundle.getStringArrayList(BUNDLE_IMAGES)
            mPosition = bundle.getInt(BUNDLE_POSITION)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDataFromIntent()
        mViewModel = PreviewPictureViewModel(this, mImages, mPosition)

        val presenter = PreviewPicturePresenter(mViewModel as PreviewPictureViewModel)
        mViewModel!!.setPresenter(presenter)

        val binding: ActivityPreviewPictureBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_preview_picture)
        binding.viewModel = mViewModel as PreviewPictureViewModel?
    }

    override fun onStart() {
        super.onStart()
        mViewModel?.onStart()
    }

    override fun onStop() {
        mViewModel?.onStop()
        super.onStop()
    }
}
