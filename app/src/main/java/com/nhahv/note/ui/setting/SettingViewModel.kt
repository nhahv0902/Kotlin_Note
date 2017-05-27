package com.nhahv.note.ui.setting

import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.ui.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class SettingViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {

  @get: Bindable
  var mImageUrl: String = ""
    set(value) {
      field = value
      notifyPropertyChanged(BR.mImageUrl)
    }

}

