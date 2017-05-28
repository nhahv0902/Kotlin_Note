package com.nhahv.note.ui.notecreation

import android.content.Context
import android.databinding.Bindable
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.ui.BaseActivity
import com.nhahv.note.ui.BaseViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NoteCreationViewModel(activity: BaseActivity) : BaseViewModel(
        activity), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {


    val mContext: Context? = activity.applicationContext
    var mCalendar = Calendar.getInstance()

    @get: Bindable
    var mNotebook: Notebook = Notebook()
        set(value) {
            field = value
            notifyPropertyChanged(BR.mNotebook)
        }

    @get: Bindable
    var mDayOfMonth: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mDayOfMonth)
        }

    @get: Bindable
    var mDayOfWeek: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mDayOfWeek)
        }

    @get: Bindable
    var mMonthYear: String? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.mMonthYear)
        }


    init {
        mNotebook.mDate = mCalendar.timeInMillis
        mNotebook.mTime = convertTimeToText(mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE))

        convertDateTime()
    }

    fun onPickPicture() {

    }

    fun onDoneCreateNotebook() {


    }

    fun onPickDate() {
        val datePicker = DatePickerDialog.newInstance(
                this,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show(mActivity.fragmentManager, "")
    }

    fun onPickTime() {
        val timePicker = TimePickerDialog.newInstance(
                this,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                false
        )
        timePicker.show(mActivity.fragmentManager, "")
    }

    override fun onDateSet(p0: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        mCalendar.set(Calendar.YEAR, year)
        mCalendar.set(Calendar.MONTH, monthOfYear)
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
// String date = "You picked the following date: "+dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
    }

    override fun onTimeSet(p0: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        mCalendar.set(Calendar.MILLISECOND, minute)
    }

    private fun convertTimeToText(hourOfDay: Int, minute: Int): String {
        val AM_PM = if (hourOfDay >= 12) "PM" else "AM"
        val hour = if (hourOfDay >= 20) "${hourOfDay - 12}" else if (hourOfDay in 12..19) "0${hourOfDay - 12}" else "0$hourOfDay"
        val minuteString = if (minute < 10) "0$minute" else "$minute"
        return "$hour:$minuteString $AM_PM"
    }

    private fun convertDateTime() {
        mDayOfMonth = "${mCalendar.get(Calendar.DAY_OF_MONTH)}"
        mDayOfWeek = mCalendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,
                Locale.getDefault())
        mMonthYear = "${mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG,
                Locale.getDefault())} ${mCalendar.get(Calendar.YEAR)}"
    }

}

