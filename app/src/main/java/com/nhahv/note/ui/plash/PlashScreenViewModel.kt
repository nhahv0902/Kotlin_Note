package com.nhahv.note.ui.plash

import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Base64
import android.util.Log
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.login.LoginActivity
import com.nhahv.note.ui.main.MainActivity
import com.nhahv.note.util.TimeUtil.TIME_DELAY
import com.nhahv.note.util.sharepreference.IS_LOGIN
import com.nhahv.note.util.sharepreference.SharePreference
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class PlashScreenViewModel(activity: PlashScreenActivity) : BaseViewModel(activity) {

  val mPreference: SharePreference = SharePreference.getIntances(activity)
  val mContext: Context = activity.applicationContext

  init {
    start()
  }

  private fun start() {
    try {
      val info = mActivity.packageManager.getPackageInfo(mActivity.packageName,
          PackageManager.GET_SIGNATURES)
      for (signature in info.signatures) {
        val md = MessageDigest.getInstance("SHA")
        md.update(signature.toByteArray())
        Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
      }
    } catch (e: PackageManager.NameNotFoundException) {
      e.printStackTrace()
    } catch (e: NoSuchAlgorithmException) {
      e.printStackTrace()
    }

    Handler().postDelayed({
      val isLogin: Boolean = mPreference[IS_LOGIN, Boolean::class.java]
      if (isLogin) {
        mContext.startActivity(MainActivity.newIntent(mContext))
      } else {
        mContext.startActivity(LoginActivity.newIntent(mContext))
      }
      mActivity.finish()
    }, TIME_DELAY.toLong())
  }

}

