package com.nhahv.note.ui.login

import android.support.v7.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*


/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class NotebookFacebook(context: AppCompatActivity, callback: LoginViewModel.ICallback) {
    val mContext = context
    val mCallback = callback

    var mCallbackManager: CallbackManager? = null

    init {
	mCallbackManager = CallbackManager.Factory.create()
	LoginManager.getInstance().registerCallback(mCallbackManager,
	    object : FacebookCallback<LoginResult> {
		override fun onSuccess(loginResult: LoginResult) {
		    handlerFacebook(loginResult.accessToken)
		}

		override fun onCancel() {
		    mCallback.onLoginError()
		}

		override fun onError(exception: FacebookException) {
		    mCallback.onLoginError()
		}
	    })
    }

    private fun handlerFacebook(token: AccessToken?) {
	val credential = FacebookAuthProvider.getCredential(token!!.token)
	FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(
	    mContext) { task ->
	    run {
		if (task.isComplete) {
		    mCallback.onLoginSuccess(FirebaseAuth.getInstance().currentUser!!)
		} else {
		    mCallback.onLoginError()
		}
	    }
	}
    }

    fun onClickLogin() {
	LoginManager.getInstance().logInWithReadPermissions(mContext,
	    Arrays.asList("public_profile"))
    }

}