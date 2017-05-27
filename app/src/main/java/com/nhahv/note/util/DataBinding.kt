package com.nhahv.note.util

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.design.widget.BottomNavigationView
import android.widget.ImageView
import android.widget.TextView
import com.nhahv.note.R
import com.nhahv.note.ui.main.MainViewModel
import com.nhahv.note.ui.notebook.NotebookAdapter
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.nhahv.note.ui.security.SecurityViewModel


/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>>
 */

@BindingAdapter("bottomNavigation")
fun bottomNavigation(view: BottomNavigationView, viewModel: MainViewModel) {
  view.setOnNavigationItemSelectedListener { menuItem ->
    when (menuItem.itemId) {
      R.id.navigation_notebook -> {
        viewModel.onStartNotebook()
      }
      R.id.navigation_note_create -> {
        viewModel.onStartNoteCreation()
      }
      R.id.navigation_setting -> {
        viewModel.onStartSetting()
      }
      else -> {
        viewModel.onStartNotebook()
      }
    }
    true
  }
}

/*
   * Set font of TextView
   * PlashActivity
   * */
@BindingAdapter("font")
fun fontFamily(view: TextView, font: String) {
  view.typeface = Typeface.createFromAsset(view.context.assets, font)
}


@BindingAdapter("imageUrl", "bindError")
fun imageUrl(view: ImageView, url: String, error: Drawable) {
  Glide.with(view.context)
      .load(url)
      .asBitmap()
      .error(error)
      .placeholder(error)
      .centerCrop()
      .into(view)
}


/*
* Bind Image Password Security
* in Activity Security
* */
@BindingAdapter("imageSecurity")
fun imageSecurity(view: AppCompatImageView, isShow: Boolean) {
  if (isShow) {
    view.setImageResource(R.drawable.icon_fill_security)
  } else {
    view.setImageResource(R.drawable.icon_default_security)
  }
}

/*bind EditText end cusor
* in Email dialog fragment
* */
@BindingAdapter("endCursor")
fun endCursor(view: AppCompatEditText, bTrue: Boolean) {
  view.setSelection(view.text.length)
}

/*bind EditText end cusor
* in Email dialog fragment
* */
@BindingAdapter("toolbar", "title")
fun toolbar(view: Toolbar, activity: AppCompatActivity, title: String) {
  activity.setSupportActionBar(view)
  activity.title = title
  activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)

}

/*bind TextView security
* in ActivitySecurity
* */
@BindingAdapter("textSecurity")
fun textSecurity(view: AppCompatTextView, typeTitle: Int) {
  val context: Context = view.context
  when (typeTitle) {
    SecurityViewModel.TITLE_WELL_COM -> {
      view.text = context.getString(R.string.text_welcome_to_app)
    }
    SecurityViewModel.TITLE_INPUT_SECURITY -> {
      view.text = context.getString(R.string.text_input_security)
    }
    SecurityViewModel.TITLE_CANCEL_SECURITY -> {
      view.text = context.getString(R.string.text_cancel_security)
    }
    SecurityViewModel.TITLE_REPEAT_SECURITY -> {
      view.text = context.getString(R.string.text_repeat_security)
    }
    else -> {
      view.text = context.getString(R.string.text_welcome_to_app)
    }
  }
}


