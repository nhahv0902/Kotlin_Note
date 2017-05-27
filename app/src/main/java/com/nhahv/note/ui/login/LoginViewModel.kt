package com.nhahv.note.ui.login

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.util.sharepreference.IS_LOGIN
import com.nhahv.note.util.sharepreference.SharePreference

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */
class LoginViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {
  val mPreference: SharePreference = SharePreference.getIntances(activity)
  val mContext: Context = activity.applicationContext

  fun onFacebookStartMain() {
    mPreference.put(IS_LOGIN, true)
    mContext.startActivity(LoginActivity.newIntent(mContext))
  }

  fun onGoogleStartMain() {
    mPreference.put(IS_LOGIN, true)
    mContext.startActivity(LoginActivity.newIntent(mContext))
  }
}