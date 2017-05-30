package com.nhahv.note.screen.login

import android.content.Context
import android.content.Intent
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseUser
import com.nhahv.note.R
import com.nhahv.note.screen.login.NotebookGoogle.Companion.RC_SIGN_IN
import com.nhahv.note.screen.main.MainActivity
import com.nhahv.note.screen.security.SecurityActivity
import com.nhahv.note.screen.security.SecurityViewModel
import com.nhahv.note.util.sharepreference.PREF_IS_LOGIN
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference
import com.nhahv.note.util.toast


/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */
class LoginViewModel(activity: LoginActivity) : LoginContract.ViewModel(activity) {

    private var mPresenter: LoginContract.Presenter? = null
    private val mPreferences = SharePreference.getInstances(activity.applicationContext)


    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        mPresenter = presenter
    }

    val mPreference: SharePreference = SharePreference.getInstances(activity)
    val mContext: Context = activity.applicationContext

    val mCallback: ICallback = object : ICallback {
        override fun onLoginSuccess(user: FirebaseUser) {
            mPreference.put(PREF_IS_LOGIN, true)
            val isSecurity: Boolean = mPreference[PREF_IS_SECURITY, Boolean::class.java]
            if (isSecurity) {
                mActivity.startActivity(
                        SecurityActivity.newIntent(mContext, SecurityViewModel.TITLE_WELL_COM))
            } else {
                mActivity.startActivity(MainActivity.newIntent(mContext))
            }
            mActivity.dismissProgress()
        }

        override fun onLoginError() {
            mContext.toast(mContext, "Login Error")
            mActivity.dismissProgress()
        }
    }
    val mFacebook = NotebookFacebook(mActivity, mCallback)
    val mGoogle = NotebookGoogle(mActivity, GoogleApiClient.OnConnectionFailedListener {
        mContext.toast(mContext, "Authentication failed.")
    })

    fun onFacebookStartMain() {
        mActivity.showProgress()
        mFacebook.onClickLogin()
    }

    fun onGoogleStartMain() {
        mActivity.showProgress()
        mGoogle.onClickLogin()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                mGoogle.handlerGoogle(result.signInAccount!!, mCallback)
            } else {
                mContext.toast(mActivity, R.string.msg_login_error)
            }
            return
        }
        mFacebook.mCallbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    interface ICallback {

        fun onLoginSuccess(user: FirebaseUser)

        fun onLoginError()
    }

}