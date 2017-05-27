package com.nhahv.note.ui.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.main.MainActivity
import com.nhahv.note.ui.security.SecurityActivity
import com.nhahv.note.ui.security.SecurityViewModel
import com.nhahv.note.util.sharepreference.PREF_IS_LOGIN
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */
class LoginViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {
  val mPreference: SharePreference = SharePreference.getInstances(activity)
  val mContext: Context = activity.applicationContext

  fun onFacebookStartMain() {
    mPreference.put(PREF_IS_LOGIN, true)
    val isSecurity: Boolean = mPreference[PREF_IS_SECURITY, Boolean::class.java]
    if (isSecurity) {
      mActivity.startActivity(
          SecurityActivity.newIntent(mContext, SecurityViewModel.TITLE_WELL_COM))
    } else {
      mActivity.startActivity(MainActivity.newIntent(mContext))
    }
  }

  fun onGoogleStartMain() {
    mPreference.put(PREF_IS_LOGIN, true)
    val isSecurity: Boolean = mPreference[PREF_IS_SECURITY, Boolean::class.java]
    if (isSecurity) {
      mActivity.startActivity(
          SecurityActivity.newIntent(mContext, SecurityViewModel.TITLE_WELL_COM))
    } else {
      mActivity.startActivity(MainActivity.newIntent(mContext))
    }
  }
}