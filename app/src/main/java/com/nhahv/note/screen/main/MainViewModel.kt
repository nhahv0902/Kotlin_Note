package com.nhahv.note.screen.main

import android.content.Context
import com.nhahv.note.R
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.BaseViewModel
import com.nhahv.note.screen.notebook.NotebookFragment
import com.nhahv.note.screen.notecreation.NoteCreationActivity
import com.nhahv.note.screen.setting.SettingFragment

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * >
 */

class MainViewModel(activity: BaseActivity) : BaseViewModel(activity) {

    val mContext: Context = activity.applicationContext

    init {
	onStartNotebook()
    }

    fun onStartNoteCreation() {
	mActivity.startActivity(NoteCreationActivity.newIntent(mContext))
    }

    fun onStartNotebook() {
	mActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_container,
	    NotebookFragment.newInstance()).commit()

    }

    fun onStartSetting() {
	mActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_container,
	    SettingFragment.newInstance()).commit()
    }
}

