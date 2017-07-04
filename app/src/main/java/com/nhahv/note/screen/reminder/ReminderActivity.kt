package com.nhahv.note.screen.reminder

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivityReminderBinding
import com.nhahv.note.screen.BaseActivity

class ReminderActivity : BaseActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, ReminderActivity::class.java)
        }
    }

    private var mViewModel: ReminderContract.ViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ReminderViewModel(this)
        val presenter = ReminderPresenter(mViewModel as ReminderViewModel)
        mViewModel?.setPresenter(presenter)

        val binding: ActivityReminderBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_reminder)
        binding.viewModel = mViewModel as ReminderViewModel
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}
