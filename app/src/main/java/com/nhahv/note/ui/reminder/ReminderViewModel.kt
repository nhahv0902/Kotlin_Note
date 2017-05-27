package com.nhahv.note.ui.reminder

import android.databinding.Bindable
import android.support.v7.app.AppCompatActivity
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.ui.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ReminderViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {

  val MAX_DAY = 7
  @get: Bindable
  var mIsSetup: Boolean = true
    set(value) {
      field = value
      notifyPropertyChanged(BR.mIsSetup)
    }

  @get:Bindable
  var mDayOfWeeks: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false)
    set(value) {
      field = value
      notifyPropertyChanged(BR.mDayOfWeeks)
    }

  init {
    for (i in 0 until MAX_DAY) {
      mDayOfWeeks[i] = false
    }
  }

  fun onClickWeekday(dayOfWeekData: Int) {
    mDayOfWeeks[dayOfWeekData] = !mDayOfWeeks[dayOfWeekData]
    notifyChange()
  }

  fun onPickDate() {
    println("Log")
  }

  fun onCheckTurnOnOffReminder(checked: Boolean) {
    mIsSetup = checked

  }
}
