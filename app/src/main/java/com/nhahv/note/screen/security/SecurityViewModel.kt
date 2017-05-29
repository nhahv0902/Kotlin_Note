package com.nhahv.note.screen.security

import android.app.Activity.RESULT_OK
import android.content.Context
import android.databinding.Bindable
import android.text.TextUtils
import android.view.View
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.screen.main.MainActivity
import com.nhahv.note.screen.security.NumberSecurity.Companion.NUMBER_0
import com.nhahv.note.screen.security.NumberSecurity.Companion.NUMBER_1
import com.nhahv.note.screen.security.NumberSecurity.Companion.NUMBER_2
import com.nhahv.note.screen.security.NumberSecurity.Companion.NUMBER_3
import com.nhahv.note.screen.security.NumberSecurity.Companion.NUMBER_4
import com.nhahv.note.screen.security.emaildialog.EmailDialogFragment
import com.nhahv.note.util.sharepreference.PREF_IS_SECURITY
import com.nhahv.note.util.sharepreference.PREF_PASSWORD_SECURITY
import com.nhahv.note.util.sharepreference.SharePreference
import com.nhahv.note.util.toast

/**
 * Created by Hoang Van Nha on 5/27/2017.
 * <>
 */

class SecurityViewModel(activity: SecurityActivity, type: Int) : SecurityContract.ViewModel(
        activity) {

    companion object {
        val TITLE_WELL_COM: Int = 1
        val TITLE_INPUT_SECURITY: Int = 2
        val TITLE_REPEAT_SECURITY: Int = 3
        val TITLE_CANCEL_SECURITY: Int = 4
    }

    val MAX_PASSWORD: Int = 4

    val mContext: Context = activity.applicationContext
    val mPreference: SharePreference = SharePreference.getInstances(mContext)
    private var mPresenter: SecurityContract.Presenter? = null

    var mPassword: String? = ""
    var mPasswordTemp: String? = ""

    @get: Bindable
    var mIsFirst: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.mIsFirst)
        }
    @get: Bindable
    var mIsSecond: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.mIsSecond)
        }

    @get: Bindable
    var mIsThree: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.mIsThree)
        }

    @get: Bindable
    var mIsFour: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.mIsFour)
        }


    @get: Bindable
    var mBackspaceVisibility: Int = View.INVISIBLE
        set(value) {
            field = value
            notifyPropertyChanged(BR.mBackspaceVisibility)
        }

    @get: Bindable
    var mDoneVisibility: Int = View.INVISIBLE
        set(value) {
            field = value
            notifyPropertyChanged(BR.mDoneVisibility)
        }


    @get: Bindable
    var mTitle: Int = type
        set(value) {
            field = value
            notifyPropertyChanged(BR.mTitle)
        }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setPresenter(presenter: SecurityContract.Presenter) {
        mPresenter = presenter
    }

    fun onClickNumber(number: Int) {
        if (mPassword?.length!! < MAX_PASSWORD) {
            mPassword = "$mPassword$number"
        }
        if (mPassword?.length == MAX_PASSWORD) {
            when (mTitle) {
                TITLE_WELL_COM -> {
                    val password: String = mPreference[PREF_PASSWORD_SECURITY, String::class.java]
                    if (TextUtils.equals(password, mPassword)) {
                        mActivity.startActivity(MainActivity.newIntent(mContext))
                    } else {
                        mContext.toast(mContext, R.string.msg_password_error)
                        mPassword = ""
                    }
                }

                TITLE_CANCEL_SECURITY -> {
                    val password: String = mPreference[PREF_PASSWORD_SECURITY, String::class.java]
                    if (TextUtils.equals(password, mPassword)) {
                        mPreference.put(PREF_IS_SECURITY, false)
                        mPreference.remove(PREF_PASSWORD_SECURITY)
                        mActivity.setResult(RESULT_OK)
                        mActivity.finish()
                    } else {
                        mContext.toast(mContext, R.string.msg_password_error)
                        mPassword = ""
                    }
                }
                TITLE_REPEAT_SECURITY -> {
                    if (!TextUtils.equals(mPassword, mPasswordTemp)) {
                        mContext.toast(mContext, R.string.msg_password_error)
                        mPassword = ""
                        mBackspaceVisibility = View.INVISIBLE
                        mDoneVisibility = View.INVISIBLE
                    }
                }
            }

        }
        if (mPassword!!.isNotEmpty()) {
            mBackspaceVisibility = View.VISIBLE
        } else {
            mBackspaceVisibility = View.INVISIBLE
        }
        setShowDone()
        updateIconSecurity()

    }

    fun onBackSpace() {
        if (mPassword!!.isNotEmpty()) {
            mPassword = mPassword?.substring(0, mPassword?.length!! - 1) ?: ""
            if (mPassword.isNullOrEmpty()) mBackspaceVisibility = View.INVISIBLE
            updateIconSecurity()
        }
        setShowDone()
    }

    fun onForgotPassword() {
    }

    private fun updateIconSecurity() {
        when (mPassword!!.length) {
            NUMBER_0 -> {
                mIsFirst = false
                mIsSecond = false
                mIsThree = false
                mIsFour = false
            }
            NUMBER_1 -> {
                mIsFirst = true
                mIsSecond = false
                mIsThree = false
                mIsFour = false
            }
            NUMBER_2 -> {
                mIsFirst = true
                mIsSecond = true
                mIsThree = false
                mIsFour = false
            }
            NUMBER_3 -> {
                mIsFirst = true
                mIsSecond = true
                mIsThree = true
                mIsFour = false
            }
            NUMBER_4 -> {
                mIsFirst = true
                mIsSecond = true
                mIsThree = true
                mIsFour = true
            }
            else -> {
                mIsFirst = false
                mIsSecond = false
                mIsThree = false
                mIsFour = false
            }
        }
    }

    private fun setShowDone() {
        when (mTitle) {
            TITLE_WELL_COM -> {

            }
            TITLE_INPUT_SECURITY -> {
                if (mPassword?.length == MAX_PASSWORD) {
                    mDoneVisibility = View.VISIBLE
                } else {
                    mDoneVisibility = View.INVISIBLE
                }

            }
            TITLE_REPEAT_SECURITY -> {
                if (mPassword?.length == MAX_PASSWORD) {
                    mDoneVisibility = View.VISIBLE
                } else {
                    mDoneVisibility = View.INVISIBLE
                }
            }
            else -> {

            }
        }
    }

    fun onDoneSecurity() {
        when (mTitle) {
            TITLE_INPUT_SECURITY -> {
                mPasswordTemp = mPassword
                mPassword = ""
                updateIconSecurity()
                setShowDone()
                mBackspaceVisibility = View.INVISIBLE
                mTitle = TITLE_REPEAT_SECURITY
                println(mPasswordTemp)
            }
            TITLE_REPEAT_SECURITY -> {
                if (mPassword.equals(mPasswordTemp)) {
                    mActivity.supportFragmentManager?.let {
                        val transaction = mActivity.supportFragmentManager.beginTransaction()
                        EmailDialogFragment.newInstance(mPasswordTemp!!).show(transaction, "")
                    }
                }
            }
        }
    }
}
