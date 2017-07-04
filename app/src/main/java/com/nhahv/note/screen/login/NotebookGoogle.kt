package com.nhahv.note.screen.login

import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.nhahv.note.R


/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>?
 */

class NotebookGoogle(activity: AppCompatActivity,
    onConnectionFailedListener: GoogleApiClient.OnConnectionFailedListener) {
    companion object {
        val RC_SIGN_IN = 990
    }

    val mActivity = activity
    var mClient: GoogleApiClient? = null

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(
            activity.getString(R.string.server_client_id)).requestEmail().build()

        mClient = GoogleApiClient.Builder(activity).enableAutoManage(activity,
            onConnectionFailedListener)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    fun onClickLogin() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mClient)
        mActivity.startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    fun handlerGoogle(acct: GoogleSignInAccount, callback: LoginViewModel.ICallback) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        FirebaseAuth.getInstance()
            .signInWithCredential(credential)
            .addOnCompleteListener(mActivity) { task ->
                run {
                    if (task.isSuccessful) {
                        callback.onLoginSuccess(FirebaseAuth.getInstance().currentUser!!)
                    } else {
                        callback.onLoginError()
                    }
                }
            }
    }
}