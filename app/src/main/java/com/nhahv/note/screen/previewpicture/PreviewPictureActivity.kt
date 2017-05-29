package com.nhahv.note.screen.previewpicture

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityPreviewPictureBinding
import com.nhahv.note.screen.BaseActivity

/**
 * PreviewPicture Screen.
 */
class PreviewPictureActivity : BaseActivity() {

    private var mViewModel: PreviewPictureContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = PreviewPictureViewModel(this)

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
