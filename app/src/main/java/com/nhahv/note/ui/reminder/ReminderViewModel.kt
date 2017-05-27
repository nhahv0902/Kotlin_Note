package com.nhahv.note.ui.reminder

import android.support.v7.app.AppCompatActivity
import com.nhahv.note.ui.BaseViewModel

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ReminderViewModel(activity: AppCompatActivity) : BaseViewModel(activity) {


  fun onClickWeekday(dayOfWeekData: Int) {
    print(dayOfWeekData)
  }

  fun onPickDate() {
    println("Log")
  }
}
