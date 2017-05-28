package com.nhahv.note.ui.reminder

import android.annotation.SuppressLint
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.ui.BaseActivity
import com.nhahv.note.ui.BaseViewModel
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*


/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ReminderViewModel(activity: BaseActivity) : BaseViewModel(
        activity), TimePickerDialog.OnTimeSetListener {

    val MAX_DAY = 7
    val mContext = activity.applicationContext
    var mHourOfDay: Int = 0
    var mMinute: Int = 0

    var mCalendar: Calendar = Calendar.getInstance()

    @get: Bindable
    var mTextTime: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.mTextTime)
        }


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
        mHourOfDay = mCalendar.get(Calendar.HOUR_OF_DAY)
        mMinute = mCalendar.get(Calendar.MINUTE)
        mTextTime = convertTimeToText(mHourOfDay, mMinute)
    }

    fun onClickWeekday(dayOfWeekData: Int) {
        mDayOfWeeks[dayOfWeekData] = !mDayOfWeeks[dayOfWeekData]
        notifyChange()
    }

    fun onPickDate() {
        @SuppressLint("WrongConstant")
        val timePicker = TimePickerDialog.newInstance(
                this,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                false
        )
        timePicker.show(mActivity.fragmentManager, "")

    }

    fun onCheckTurnOnOffReminder(checked: Boolean) {
        mIsSetup = checked

    }

    override fun onTimeSet(p0: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        mHourOfDay = hourOfDay
        mMinute = minute
        mTextTime = convertTimeToText(hourOfDay, minute)
    }

    private fun convertTimeToText(hourOfDay: Int, minute: Int): String {
        val AM_PM = if (hourOfDay >= 12) "PM" else "AM"
        val hour = if (hourOfDay >= 20) "${hourOfDay - 12}" else if (hourOfDay in 12..19) "0${hourOfDay - 12}" else "0$hourOfDay"
        val minuteString = if (minute < 10) "0$minute" else "$minute"
        mCalendar.set(Calendar.HOUR_OF_DAY, mHourOfDay)
        mCalendar.set(Calendar.MILLISECOND, mMinute)
        return "$hour:$minuteString $AM_PM"
    }
}
