package com.nhahv.note.screen

import android.support.v7.app.AppCompatActivity
import com.nhahv.note.widget.NoteProgress

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

open class BaseActivity : AppCompatActivity() {
    private var mProgressDialog: NoteProgress? = null

    fun showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = NoteProgress(this)
        }
        mProgressDialog?.let {
            if (!it.isShowing) it.show()
        }
    }

    fun dismissProgress() {
        mProgressDialog?.let {
            if (it.isShowing) it.dismiss()
        }
    }
}
