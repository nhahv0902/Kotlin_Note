package com.nhahv.note.screen.main

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import com.nhahv.note.screen.notebook.NotebookFragment
import com.nhahv.note.screen.notecreation.NoteCreationActivity
import com.nhahv.note.screen.setting.SettingFragment
import com.nhahv.note.util.Request.REQUEST_CREATE_NOTE

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * >
 */

class MainViewModel(activity: MainActivity) : MainContract.ViewModel(activity) {

    private val mContext: Context = activity.applicationContext
    private var mPresenter: MainContract.Presenter? = null
    private val mNotebookFragment = NotebookFragment.newInstance()
    private val mSettingFragment = SettingFragment.newInstance()
    private val mFragment = arrayListOf(mNotebookFragment, mSettingFragment)
    var mAdapter = MainViewPagerAdapter(activity.supportFragmentManager, mFragment)

    override fun onStart() {}

    override fun onStop() {}

    override fun setPresenter(presenter: MainContract.Presenter) {
        mPresenter = presenter
    }

    fun onStartNoteCreation() {
        mActivity.startActivityForResult(NoteCreationActivity.newIntent(mContext),
            REQUEST_CREATE_NOTE)
    }

    fun onStartNotebook() {
        /* mActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_container,
                 NotebookFragment.newInstance()).commit()*/

    }

    fun onStartSetting() {
        /*  mActivity.supportFragmentManager.beginTransaction().replace(R.id.frame_container,
                  SettingFragment.newInstance()).commit()*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CREATE_NOTE) {
            mNotebookFragment.let {
                mNotebookFragment.mViewModel?.onLoadNotebookData()
            }
        }
    }
}

