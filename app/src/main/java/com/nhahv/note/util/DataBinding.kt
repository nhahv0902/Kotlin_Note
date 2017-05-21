package com.nhahv.note.util

import android.databinding.BindingAdapter
import android.support.design.widget.BottomNavigationView
import com.nhahv.note.R
import com.nhahv.note.ui.main.MainViewModel

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>>
 */

@BindingAdapter("bottomNavigation")
fun bottomNavigation(view: BottomNavigationView, viewModel: MainViewModel) {
  view.setOnNavigationItemSelectedListener { menuItem ->
    when (menuItem.itemId) {
      R.id.navigation_notebook -> {
      }
      R.id.navigation_note_create -> {
      }
      R.id.navigation_setting -> {
      }
      else -> {
      }
    }
    true
  }
}


