package com.nhahv.note.ui.security.emaildialog

import android.app.Activity.RESULT_OK
import android.content.Context
import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.ui.BaseActivity
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.PREF_PASSWORD_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference
import com.nhahv.note.util.toast

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>
 */
class EmailDialogViewModel(activity: BaseActivity,
    dialog: EmailDialogFragment, password: String) : BaseViewModel(activity) {

    private val mContext: Context = activity.applicationContext
    private val mPassword: String = password
    private val mDialog: EmailDialogFragment = dialog
    @get: Bindable
    var mEmail: String? = ""
	set(value) {
	    field = value
	    notifyPropertyChanged(BR.mEmail)
	}

    private val mPreference = SharePreference.getInstances(mContext)

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
