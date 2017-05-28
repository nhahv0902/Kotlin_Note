package com.nhahv.note.ui.security

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nhahv.note.R
import com.nhahv.note.databinding.ActivitySecurityBinding
import com.nhahv.note.ui.BaseActivity
import com.nhahv.note.ui.security.SecurityViewModel.Companion.TITLE_WELL_COM
import com.nhahv.note.util.BundleConstant.BUNDLE_SECURITY_TYPE

class SecurityActivity : BaseActivity() {

    var mViewModel: SecurityViewModel? = null
    var mTypeSecurity: Int = TITLE_WELL_COM

    companion object {
	fun newIntent(context: Context, key: Int): Intent {
	    val intent: Intent = Intent(context, SecurityActivity::class.java)
	    val bundle: Bundle = Bundle()
	    bundle.putInt(BUNDLE_SECURITY_TYPE, key)
	    intent.putExtras(bundle)
	    return intent
	}
    }

    private fun getDataFromIntent() {
	val bundle: Bundle? = intent.extras
	bundle?.let {
	    mTypeSecurity = bundle.getInt(BUNDLE_SECURITY_TYPE)
	}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	getDataFromIntent()
	val binding: ActivitySecurityBinding = DataBindingUtil.setContentView(this,
	    R.layout.activity_security)
	mViewModel = SecurityViewModel(this, mTypeSecurity)
	binding.viewModel = mViewModel
    }
}
