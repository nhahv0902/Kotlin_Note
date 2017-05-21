package com.nhahv.note.ui.main

import com.nhahv.note.R
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.notebook.NotebookFragment
import com.nhahv.note.ui.setting.SettingFragment

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * >
 */

class MainViewModel(activity: MainActivity) : BaseViewModel(activity) {

  init {
    onStartNotebook()
  }

  fun onStartNoteCreation() {

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

