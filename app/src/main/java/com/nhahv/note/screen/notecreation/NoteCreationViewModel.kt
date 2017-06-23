package com.nhahv.note.screen.notecreation

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.Bindable
import android.location.Address
import android.location.Location
import android.support.v4.content.ContextCompat
import android.util.Log
import com.android.databinding.library.baseAdapters.BR
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.location.places.ui.PlaceAutocomplete
import com.nhahv.note.R
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.creation.NotebookDataSource
import com.nhahv.note.screen.loadpicture.folder.AlbumActivity
import com.nhahv.note.screen.notecreation.preview.NotePreviewActivity
import com.nhahv.note.util.BundleConstant.BUNDLE_IMAGES
import com.nhahv.note.util.DataUtil.NOTE_TAG
import com.nhahv.note.util.Request.REQUEST_NOTE_PREVIEW
import com.nhahv.note.util.Request.REQUEST_PICK_IMAGE
import com.nhahv.note.util.Request.REQUEST_PLACE_ADDRESS
import com.nhahv.note.util.log
import com.nhahv.note.util.mHashPermission
import com.nhahv.note.util.toast
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import io.nlopez.smartlocation.OnLocationUpdatedListener
import io.nlopez.smartlocation.SmartLocation
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>
 */

class NoteCreationViewModel(activity: NoteCreationActivity) : NoteCreationContract.ViewModel(
        activity), TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, OnLocationUpdatedListener {

    private var mPresenter: NoteCreationContract.Presenter? = null
    private val mContext: Context = activity.applicationContext
    private var mCalendar: Calendar = Calendar.getInstance()
    private var mProvider: LocationGooglePlayServicesProvider? = null
    private val mImageMap: HashMap<String, String> = HashMap()

    @get: Bindable
    var mAdapter: ViewPagerAdapter = ViewPagerAdapter(mImageMap)
        set(value) {
            field = value
            notifyPropertyChanged(BR.mAdapter)
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

    @get : Bindable
    var mImageSize: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.mImageSize)
        }

    init {
        mNotebook.mDate = mCalendar.timeInMillis
        mNotebook.mTime = convertTimeToText(mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE))

        convertDateTime()
    }

    override fun onStart() {

    }

    override fun onStop() {
        SmartLocation.with(mContext).location().stop()
        mProvider = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != RESULT_OK || data == null || data.extras == null) return
        when (requestCode) {
            REQUEST_PICK_IMAGE -> {
                val images: ArrayList<String> = data.extras.getStringArrayList(BUNDLE_IMAGES)
                images.filterNot { mImageMap.containsKey(it) }
                        .forEach { mImageMap.put(it, it) }
                mAdapter.update(mImageMap)
                mImageSize = mAdapter.count
            }
            REQUEST_NOTE_PREVIEW -> {
                val images: ArrayList<String> = data.extras.getStringArrayList(BUNDLE_IMAGES)
                mImageMap.clear()
                images.filterNot { mImageMap.containsKey(it) }
                        .forEach { mImageMap.put(it, it) }
                mAdapter.update(mImageMap)
                mImageSize = mAdapter.count
            }
            REQUEST_PLACE_ADDRESS -> {
                // get location address search place
                val place = PlaceAutocomplete.getPlace(mActivity, data)
                mNotebook.mPlace = place.address.toString()
            }
            PlaceAutocomplete.RESULT_ERROR -> {
                val status = PlaceAutocomplete.getStatus(mActivity, data)
                Log.d(NOTE_TAG, status.statusMessage)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            mHashPermission[Manifest.permission.ACCESS_FINE_LOCATION] -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startLocation()
                } else {
                    mContext.toast(mContext, R.string.msg_denied_access_fine_location)
                }
                return
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
        mActivity.showProgress()
        mNotebook.mPictures.addAll(mImageMap.values)
        mPresenter?.addNotebook(mNotebook, object : NotebookDataSource.Callback {
            override fun onSuccess() {
                mContext.toast(mContext, "Add success")
                mActivity.dismissProgress()
                mActivity.setResult(RESULT_OK)
                mActivity.finish()
            }

            override fun onError() {
                mContext.toast(mContext, "Add error")
                mActivity.dismissProgress()
            }
        })
    }

    override fun onPreviewImage() {
        mActivity.startActivityForResult(
                NotePreviewActivity.newIntent(mContext, ArrayList(mImageMap.values)),
                REQUEST_NOTE_PREVIEW)
    }

    fun onPickDate() {
        val datePicker = DatePickerDialog.newInstance(
                this,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.accentColor = ContextCompat.getColor(mContext, R.color.colorPrimary)
        datePicker.show(mActivity.fragmentManager, "")
    }

    fun onPickTime() {
        val timePicker = TimePickerDialog.newInstance(
                this,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                false
        )
        timePicker.accentColor = ContextCompat.getColor(mContext, R.color.colorPrimary)
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
        mMonthYear = "${mCalendar.getDisplayName(Calendar.MONTH, Calendar.SHORT,
                Locale.getDefault())} ${mCalendar.get(Calendar.YEAR)}"

        mNotebook.mDate = mCalendar.timeInMillis
    }

    override fun onUpPictureError() {
        mContext.toast(mContext, "upload picture error")
        mActivity.dismissProgress()
    }

    override fun onUpPictureSuccess() {
        mContext.toast(mContext, "upload picture success")
        mActivity.dismissProgress()
    }

    override fun onSearchPlaceAddress() {
        try {
            val intent: Intent = PlaceAutocomplete.IntentBuilder(
                    PlaceAutocomplete.MODE_OVERLAY).build(
                    mActivity)
            mActivity.startActivityForResult(intent, REQUEST_PLACE_ADDRESS)
        } catch (exception: GooglePlayServicesRepairableException) {
            mActivity.toast(mActivity.applicationContext,
                    R.string.msg_device_not_support_place_address)
        } catch (exception: GooglePlayServicesNotAvailableException) {
            mActivity.toast(mActivity.applicationContext,
                    R.string.msg_device_not_support_place_address)
        }
    }

    override fun onGetAddressSuccess(address: String) {
        mNotebook.mPlace = address
    }

    override fun onGetAddressError() {
        mContext.log(mContext.getString(R.string.msg_load_current_address_error))
    }

    /*
    * Start Location Smart Lib
    * */
    override fun startLocation() {
        if (mProvider == null) {
            mProvider = LocationGooglePlayServicesProvider()
        }
        mProvider?.setCheckLocationSettings(true)
        val smartLocation: SmartLocation = SmartLocation.Builder(mContext).logging(true).build()
        smartLocation.location(mProvider).start(this)
    }

    override fun onLocationUpdated(location: Location?) {
        location?.let {
            SmartLocation.with(mContext).geocoding().reverse(location) { _, result ->
                result?.let {
                    if (result.size > 0) {
                        val address: Address = result[0]
                        val addressString: StringBuilder = StringBuilder()
                        for (index in 0 until address.maxAddressLineIndex - 1) {
                            if (index == address.maxAddressLineIndex - 2) {
                                addressString.append(address.getAddressLine(index))
                            } else {
                                addressString.append(address.getAddressLine(index)).append(", ")
                            }
                        }
                        mNotebook.mPlace = addressString.toString()
                    }
                }
            }
        }
    }
}

