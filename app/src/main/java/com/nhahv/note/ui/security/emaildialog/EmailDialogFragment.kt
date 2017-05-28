package com.nhahv.note.ui.security.emaildialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.nhahv.note.databinding.FragmentEmailDialogBinding
import com.nhahv.note.ui.BaseActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_PASSWORD

/**
 * A simple [Fragment] subclass.
 */
class EmailDialogFragment : DialogFragment() {

    private var mViewModel: EmailDialogViewModel? = null

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
	if (dialog != null && dialog.window != null) {
	    dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
	}
	val binding = FragmentEmailDialogBinding.inflate(inflater, container, false)
	binding.viewModel = mViewModel
	return binding.root
    }
}
