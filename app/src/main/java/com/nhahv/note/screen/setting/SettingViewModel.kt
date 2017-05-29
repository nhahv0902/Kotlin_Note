package com.nhahv.note.screen.setting

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.Bindable
import android.support.v4.app.Fragment
import com.android.databinding.library.baseAdapters.BR
import com.google.firebase.auth.FirebaseAuth
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.BaseViewModel
import com.nhahv.note.screen.login.LoginActivity
import com.nhahv.note.screen.reminder.ReminderActivity
import com.nhahv.note.screen.security.SecurityActivity
import com.nhahv.note.screen.security.SecurityViewModel.Companion.TITLE_CANCEL_SECURITY
import com.nhahv.note.screen.security.SecurityViewModel.Companion.TITLE_INPUT_SECURITY
import com.nhahv.note.util.Request.REQUEST_REMINDER
import com.nhahv.note.util.Request.REQUEST_SECURITY
import com.nhahv.note.util.sharepreference.PREF_IS_LOGIN
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class SettingViewModel(activity: BaseActivity,
        fragment: SettingFragment) : SettingContract.ViewModel(
        activity) {

    private var mPresenter: SettingContract.Presenter? = null
    val mContext: Context = activity.applicationContext
    val mFragment: Fragment = fragment
    val mPreferences = SharePreference.getInstances(mContext)
    var mUser = FirebaseAuth.getInstance().currentUser

    @get : Bindable
    var mChecked: Boolean = mPreferences[PREF_IS_SECURITY, Boolean::class.java]
        set(value) {
            field = value
            notifyPropertyChanged(BR.mChecked)
        }

    @get: Bindable
    var mImageUrl: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mImageUrl)
        }

    init {
    }

    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun setPresenter(presenter: SettingContract.Presenter) {
        mPresenter = presenter
    }

    fun onEditInfo() {

    }

    fun onReminder() {
        mFragment.startActivityForResult(ReminderActivity.newIntent(mContext), REQUEST_REMINDER)
    }

    fun onCheckedReminder(checked: Boolean) {
    }

    fun onClickSecurity() {
        if (mChecked) {
            mFragment.startActivityForResult(
                    SecurityActivity.newIntent(mContext, TITLE_CANCEL_SECURITY),
                    REQUEST_SECURITY)
        } else {
            mFragment.startActivityForResult(
                    SecurityActivity.newIntent(mContext, TITLE_INPUT_SECURITY),
                    REQUEST_SECURITY)
        }
    }

    fun onChangeLanguage() {
    }

    fun onLogout() {
        mPreferences.remove(PREF_IS_LOGIN)
        FirebaseAuth.getInstance().signOut()
        mActivity.startActivity(LoginActivity.newIntent(mContext))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK) return

        when (requestCode) {
            REQUEST_SECURITY -> {
                mChecked = mPreferences[PREF_IS_SECURITY, Boolean::class.java]
            }
            REQUEST_REMINDER -> {
            }
        }
    }

}

