package com.nhahv.note.ui.reminder

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityReminderBinding

class ReminderActivity : AppCompatActivity() {

  companion object {
    fun newIntent(context: Context): Intent {
      return Intent(context, ReminderActivity::class.java)
    }
  }

  private var mViewModel: ReminderViewModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding: ActivityReminderBinding = DataBindingUtil.setContentView(this,
        R.layout.activity_reminder)
    mViewModel = ReminderViewModel(this)
    binding.viewModel = mViewModel
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    if (item?.itemId == android.R.id.home) onBackPressed()
    return super.onOptionsItemSelected(item)
  }
}
