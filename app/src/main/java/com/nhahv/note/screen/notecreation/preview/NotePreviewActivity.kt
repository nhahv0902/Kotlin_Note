package com.nhahv.note.screen.notecreation.preview

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityNotePreviewBinding
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES

/**
 * NotePreview Screen.
 */
class NotePreviewActivity : BaseActivity() {

    private var mViewModel: NotePreviewContract.ViewModel? = null

    companion object {
        fun newIntent(context: Context, images: ArrayList<String>): Intent {
            val intent = Intent(context, NotePreviewActivity::class.java)
            val bundle = Bundle()
            bundle.putStringArrayList(BUNDLE_IMAGES, images)
            intent.putExtras(bundle)
            return intent
        }
    }

    private fun getImages(): ArrayList<String>? {
        return intent.extras.getStringArrayList(BUNDLE_IMAGES) ?: null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = NotePreviewViewModel(this, getImages())

        val presenter = NotePreviewPresenter(mViewModel as NotePreviewViewModel)
        mViewModel?.setPresenter(presenter)

        val binding = DataBindingUtil.setContentView<ActivityNotePreviewBinding>(this,
                R.layout.activity_note_preview)
        binding.viewModel = mViewModel as NotePreviewViewModel?
    }

    override fun onStart() {
        super.onStart()
        mViewModel?.onStart()
    }

    override fun onStop() {
        mViewModel?.onStop()
        super.onStop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mViewModel?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        mViewModel?.onBackPressed()
        super.onBackPressed()
    }
}
