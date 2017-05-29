package com.nhahv.note.screen.security.emaildialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.nhahv.note.databinding.FragmentEmailDialogBinding
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_PASSWORD

/**
 * A simple [Fragment] subclass.
 */
class EmailDialogFragment : DialogFragment() {

    private var mViewModel: EmailDialogContract.ViewModel? = null

    companion object {
        fun newInstance(password: String): EmailDialogFragment {
            val bundle: Bundle = Bundle()
            bundle.putString(BUNDLE_PASSWORD, password)
            val fragment = EmailDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private fun getPassword(): String {
        val bundle: Bundle = arguments
        return bundle.getString(BUNDLE_PASSWORD)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        mViewModel = EmailDialogViewModel(activity as BaseActivity, this, getPassword())
        val presenter = EmailDialogPresenter(mViewModel as EmailDialogViewModel)
        mViewModel?.setPresenter(presenter)


        if (dialog != null && dialog.window != null) {
            dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        }
        val binding = FragmentEmailDialogBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel as EmailDialogViewModel
        return binding.root
    }
}
