package com.nhahv.note.util

import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.nhahv.note.R
import com.nhahv.note.ui.main.MainViewModel
import com.nhahv.note.ui.notebook.NotebookAdapter

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





