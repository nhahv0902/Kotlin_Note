package com.nhahv.note.screen.notecreation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.databinding.ObservableField
import com.android.databinding.library.baseAdapters.BR
import com.nhahv.note.R
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.screen.loadpicture.folder.AlbumActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES
import com.nhahv.note.util.Request.REQUEST_PICK_IMAGE
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*

/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NoteCreationViewModel(activity: NoteCreationActivity) : NoteCreationContract.ViewModel(
        activity), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private var mPresenter: NoteCreationContract.Presenter? = null

    val mContext: Context = activity.applicationContext
    var mCalendar: Calendar = Calendar.getInstance()
    var mImages: ObservableArrayList<String> = ObservableArrayList()
    var mAdapter: ObservableField<ViewPagerAdapter> = ObservableField()


    @get: Bindable
    var mHeight: Int = mContext.resources.getDimension(R.dimen.dp_56).toInt()
        set(value) {
            field = value
            notifyPropertyChanged(BR.mHeight)
        }
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

        mAdapter.set(ViewPagerAdapter(mImages))
        convertDateTime()
    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK || data == null || data.extras == null) return
        when (requestCode) {
            REQUEST_PICK_IMAGE -> {
                mImages.addAll(data.extras.getStringArrayList(BUNDLE_IMAGES))
                mAdapter.get().notifyDataSetChanged()
                mHeight = mContext.resources.getDimension(
                        if (mImages.size > 0) R.dimen.dp_270 else R.dimen.dp_56).toInt()
            }

        }
    }

    override fun setPresenter(presenter: NoteCreationContract.Presenter) {
        mPresenter = presenter
    }

    override fun onPickPicture() {
        mActivity.startActivityForResult(AlbumActivity.newIntent(mContext), REQUEST_PICK_IMAGE)
    }

    fun onDoneCreateNotebook() {
        mNotebook.mPlace = "Số nhà 56, ngõ 105, Doãn Kế Thiện, Dịch Vọng, Cầu Giấy, Hà Nội"
        mPresenter?.addNotebook(mNotebook)
    }

    override fun onPreviewImage() {
//        mActivity.startActivity(PreviewPictureActivity.newIntent(mContext, mImages, ))
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
        convertDateTime()
    }

    override fun onTimeSet(p0: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        mCalendar.set(Calendar.MILLISECOND, minute)
        mNotebook.mTime = convertTimeToText(hourOfDay, minute)
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

        mNotebook.mDate = mCalendar.timeInMillis
    }

}

