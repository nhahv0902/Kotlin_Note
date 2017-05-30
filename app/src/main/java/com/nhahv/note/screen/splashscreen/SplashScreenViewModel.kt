package com.nhahv.note.screen.splashscreen

import android.content.Context
import android.os.Handler
import com.nhahv.note.screen.login.LoginActivity
import com.nhahv.note.screen.main.MainActivity
import com.nhahv.note.screen.security.SecurityActivity
import com.nhahv.note.screen.security.SecurityViewModel
import com.nhahv.note.util.TimeUtil
import com.nhahv.note.util.sharepreference.PREF_IS_LOGIN
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference

/**
 * Exposes the data to be used in the SplashScreen screen.
 */

class SplashScreenViewModel(activity: SplashScreenActivity) : SplashScreenContract.ViewModel(
        activity) {

    private var mPresenter: SplashScreenContract.Presenter? = null
    private var mContext: Context = activity.applicationContext
    private var mPreference = SharePreference.getInstances(activity.applicationContext)

    init {
        start()
    }

    fun start() {
        /*  try {
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
          }*/

        Handler().postDelayed({
            val isLogin: Boolean = mPreference[PREF_IS_LOGIN, Boolean::class.java]
            if (isLogin) {
                val isSecurity: Boolean = mPreference[PREF_IS_SECURITY, Boolean::class.java]
                if (isSecurity) {
                    mActivity.startActivity(
                            SecurityActivity.newIntent(mContext, SecurityViewModel.TITLE_WELL_COM))
                } else {
                    mActivity.startActivity(MainActivity.newIntent(mContext))
                }
            } else {
                mActivity.startActivity(LoginActivity.newIntent(mContext))
            }
            mActivity.finish()
        }, TimeUtil.TIME_DELAY)

    }

    override fun onStart() {
        mPresenter?.onStart()
    }

    override fun onStop() {
        mPresenter?.onStop()
    }

    override fun setPresenter(presenter: SplashScreenContract.Presenter) {
        mPresenter = presenter
    }
}
