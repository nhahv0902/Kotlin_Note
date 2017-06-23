package com.nhahv.note.screen.security.emaildialog

import android.app.Activity.RESULT_OK
import android.content.Context
import android.databinding.Bindable
import android.text.TextUtils
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.PREF_PASSWORD_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference
import com.nhahv.note.util.toast

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>
 */
class EmailDialogViewModel(activity: BaseActivity,
                           dialog: EmailDialogFragment, password: String) : EmailDialogContract.ViewModel(activity) {

    private val mContext: Context = activity.applicationContext
    private val mPassword: String = password
    private val mDialog: EmailDialogFragment = dialog
    private var mPresenter: EmailDialogContract.Presenter? = null

    @get: Bindable
    var mEmail: String? = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mEmail)
        }

    private val mPreference = SharePreference.getInstances(mContext)


    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun setPresenter(presenter: EmailDialogContract.Presenter) {
        mPresenter = presenter
    }

    fun onClickDisAgree() {
        mDialog.dismiss()
    }

    fun onClickAgree() {
        if (!TextUtils.isEmpty(mEmail)
                && android.util.Patterns.EMAIL_ADDRESS.matcher(mEmail?.trim()).matches()) {
            mPreference.put(PREF_PASSWORD_SECURITY, mPassword)
            mPreference.put(PREF_IS_SECURITY, true)
            mDialog.dismiss()
            mActivity.setResult(RESULT_OK)
            mActivity.finish()
        } else {
            mContext.toast(mContext, R.string.msg_email_matchs)
        }
    }
}
