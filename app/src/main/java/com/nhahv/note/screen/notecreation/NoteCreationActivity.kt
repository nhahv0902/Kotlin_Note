package com.nhahv.note.screen.notecreation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityNoteCreationBinding
import com.nhahv.note.screen.BaseActivity


class NoteCreationActivity : BaseActivity() {

    var mViewModel: NoteCreationContract.ViewModel? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NoteCreationActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = NoteCreationViewModel(this)
        val presenter = NoteCreationPresenter(mViewModel as NoteCreationViewModel)
        mViewModel?.setPresenter(presenter)


        val binding: ActivityNoteCreationBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_note_creation)
        binding.viewModel = mViewModel as NoteCreationViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.note_creation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.action_pick_image -> {
                mViewModel?.onPickPicture()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
