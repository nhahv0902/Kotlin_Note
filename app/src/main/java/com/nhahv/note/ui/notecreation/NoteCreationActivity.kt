package com.nhahv.note.ui.notecreation

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityNoteCreationBinding

class NoteCreationActivity : AppCompatActivity() {

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, NoteCreationActivity::class.java)
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityNoteCreationBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_note_creation)
    binding.viewModel = NoteCreationViewModel(this)
  }
}
