package com.nhahv.note.util

import android.content.Context
import android.databinding.BindingAdapter
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.nhahv.note.R
import com.nhahv.note.screen.BaseActivity
import com.nhahv.note.screen.main.MainViewModel
import com.nhahv.note.screen.notebook.NotebookViewModel
import com.nhahv.note.screen.security.SecurityViewModel
import org.apache.commons.lang3.StringUtils
import java.util.*


/**
 * Created by Hoang Van Nha on 5/21/2017.
 * <>>
 */


/*
* Custom margin in TextView
* LoginActivity
* */
@BindingAdapter("layout_marginTop")
fun marginTop(view: View, marginTop: Float) {
    val layoutParams: ViewGroup.MarginLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
    layoutParams.topMargin = marginTop.toInt()
    view.layoutParams = layoutParams
}


/*
* Binding bottom navigation view and viewpager in main
* MainActivity
* */
@BindingAdapter("bottomNavigation", "viewPager")
fun bottomNavigation(view: BottomNavigationView, viewModel: MainViewModel, viewPager: ViewPager) {
    view.setOnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_notebook -> {
                viewModel.onStartNotebook()
                viewPager.currentItem = 0
            }
            R.id.navigation_note_create -> {
                viewModel.onStartNoteCreation()
            }
            R.id.navigation_setting -> {
                viewPager.currentItem = 1
            }
        }
        true
    }
    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {}

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

        override fun onPageSelected(position: Int) {
            view.selectedItemId = if (position == 0) R.id.navigation_notebook
            else R.id.navigation_setting
        }
    })
}

/*
   * Set font of TextView
   * PlashActivity
   * */
@BindingAdapter("font")
fun fontFamily(view: TextView, font: String) {
    view.typeface = Typeface.createFromAsset(view.context.assets, font)
}


@BindingAdapter(value = *arrayOf("imageUrl", "imageUri", "bindError"), requireAll = false)
fun imageUrl(view: ImageView, url: String?, uri: Uri?, error: Drawable) {
    Glide.with(view.context)
            .load(url ?: uri)
            .asBitmap()
            .error(error)
            .placeholder(error)
            .dontTransform()
            .dontAnimate()
            .thumbnail(0.5F)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .centerCrop()
            .into(view)
}

/*
* Bind Image Password Security
* in Activity Security
* */
@BindingAdapter("imageSecurity")
fun imageSecurity(view: AppCompatImageView, isShow: Boolean) {
    if (isShow) {
        view.setImageResource(R.drawable.icon_fill_security)
    } else {
        view.setImageResource(R.drawable.icon_default_security)
    }
}

/*bind EditText end cusor
* in Email dialog fragment
* */
@BindingAdapter("endCursor")
fun endCursor(view: AppCompatEditText, bTrue: Boolean) {
    view.setSelection(view.text.length)
}

/*bind EditText end cusor
* in Email dialog fragment
* */
@BindingAdapter(value = *arrayOf("toolbar", "title"), requireAll = false)
fun toolbar(view: Toolbar, activity: BaseActivity, title: String?) {
    activity.setSupportActionBar(view)
    activity.title = StringUtils.capitalize(title)
    activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

/*bind TextView security
* in ActivitySecurity
* */
@BindingAdapter("textSecurity")
fun textSecurity(view: AppCompatTextView, typeTitle: Int) {
    val context: Context = view.context
    when (typeTitle) {
        SecurityViewModel.TITLE_WELL_COM -> {
            view.text = context.getString(R.string.text_welcome_to_app)
        }
        SecurityViewModel.TITLE_INPUT_SECURITY -> {
            view.text = context.getString(R.string.text_input_security)
        }
        SecurityViewModel.TITLE_CANCEL_SECURITY -> {
            view.text = context.getString(R.string.text_cancel_security)
        }
        SecurityViewModel.TITLE_REPEAT_SECURITY -> {
            view.text = context.getString(R.string.text_repeat_security)
        }
        else -> {
            view.text = context.getString(R.string.text_welcome_to_app)
        }
    }
}

/*
* bind current item of ViewPagerAdapter
* in PreviewPictureActivity
* */
@BindingAdapter("adapter", "currentItem")
fun currentItem(view: ViewPager, adapter: FragmentPagerAdapter?, position: Int) {
    adapter?.let {
        view.adapter = adapter
        view.currentItem = position
    }
}

@BindingAdapter("swipeRefreshLayout", "refresh")
fun swipeRefreshLayout(view: SwipeRefreshLayout, viewModel: NotebookViewModel, isRefresh: Boolean) {
    view.setColorSchemeResources(R.color.colorPrimary)
    view.isRefreshing = isRefresh
    view.setOnRefreshListener {
        view.isRefreshing = true
        viewModel.onLoadNotebookData()
    }
}

/*
* bind date notebook in item_notebook
*
* */

@BindingAdapter(value = *arrayOf("textDayOfMonth"), requireAll = false)
fun textDateOfNotebook(view: AppCompatTextView, date: Long) {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    view.text = "${calendar.get(Calendar.DAY_OF_MONTH)}"

}

@BindingAdapter(value = *arrayOf("textMonth"), requireAll = false)
fun textMonth(view: AppCompatTextView, date: Long) {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    view.text = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
}

@BindingAdapter(value = *arrayOf("textYear"), requireAll = false)
fun textYear(view: AppCompatTextView, date: Long) {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    view.text = "${calendar.get(Calendar.YEAR)}"
}

@BindingAdapter("dayOfWeek")
fun dayOfWeek(view: AppCompatTextView, date: Long) {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    view.text = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault())
}

/*
* override Layout height in View
* in NoteCreationActivity
* */
@BindingAdapter("layout_height")
fun setLayoutHeight(view: View, height: Float) {
    val layoutParams: ViewGroup.LayoutParams = view.layoutParams
    layoutParams.height = height.toInt()
    view.layoutParams = layoutParams
}

/*
* Place Prediction
* in Layout Note create
* */
