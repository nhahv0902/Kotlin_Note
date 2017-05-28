package com.nhahv.note.ui.plash

import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Base64
import android.util.Log
import com.nhahv.note.ui.BaseViewModel
import com.nhahv.note.ui.loadpicture.LoaderPicture
import com.nhahv.note.ui.loadpicture.model.Folder
import com.nhahv.note.ui.loadpicture.model.ImageFolder
import com.nhahv.note.ui.login.LoginActivity
import com.nhahv.note.ui.main.MainActivity
import com.nhahv.note.ui.security.SecurityActivity
import com.nhahv.note.ui.security.SecurityViewModel.Companion.TITLE_WELL_COM
import com.nhahv.note.util.DataUtil.NOTE_TAG
import com.nhahv.note.util.TimeUtil.TIME_DELAY
import com.nhahv.note.util.sharepreference.PREF_IS_LOGIN
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference
import org.jetbrains.anko.custom.async
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class PlashScreenViewModel(activity: PlashScreenActivity) : BaseViewModel(activity) {

    val mPreference: SharePreference = SharePreference.getInstances(activity)
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

	/*Handler().postDelayed({
	    val isLogin: Boolean = mPreference[PREF_IS_LOGIN, Boolean::class.java]
	    if (isLogin) {
		val isSecurity: Boolean = mPreference[PREF_IS_SECURITY, Boolean::class.java]
		if (isSecurity) {
		    mActivity.startActivity(SecurityActivity.newIntent(mContext, TITLE_WELL_COM))
		} else {
		    mActivity.startActivity(MainActivity.newIntent(mContext))
		}
	    } else {
		mActivity.startActivity(LoginActivity.newIntent(mContext))
	    }
	    mActivity.finish()
	}, TIME_DELAY)*/

	val loaderPicture = LoaderPicture(mContext)
	async {
	    val folders: ArrayList<Folder> = loaderPicture.loadImages()
	    for ((name, images) in folders) {
		Log.d(NOTE_TAG, "folder = $name  -  - ${images.size}")
	    }
	}
    }

}

