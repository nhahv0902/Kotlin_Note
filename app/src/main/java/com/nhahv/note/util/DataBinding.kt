package com.nhahv.note.util

import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import com.nhahv.note.R
import com.nhahv.note.ui.main.MainViewModel
import com.nhahv.note.ui.notebook.NotebookAdapter
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v4.graphics.drawable.RoundedBitmapDrawable
import android.graphics.Bitmap
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop


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




