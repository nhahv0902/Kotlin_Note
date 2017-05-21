package com.nhahv.note.ui.plash

import android.content.pm.PackageManager
import android.os.Handler
import android.util.Base64
import android.util.Log
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.main.MainActivity
import com.nhahv.note.util.TimeUtil.TIME_DELAY
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class PlashScreenViewModel(activity: PlashScreenActivity) : BaseViewModel(activity) {

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
      /* if (SharePreference.getInstances(mActivity).getBoolean(PREF_IS_LOGIN)) {

       } else {
         mActivity.startActivity(LoginActivity.getIntent(mActivity))
       }*/
      mActivity.startActivity(MainActivity.newIntent(mActivity))
      mActivity.finish()
    }, TIME_DELAY.toLong())
  }

}

