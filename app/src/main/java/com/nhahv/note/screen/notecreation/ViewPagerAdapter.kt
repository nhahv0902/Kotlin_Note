package com.nhahv.note.screen.notecreation

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import uk.co.senab.photoview.PhotoView

/**
 * Created by Hoang Van Nha on 5/30/2017.
 * <>
 */
class ViewPagerAdapter(images: HashMap<String, String>) : PagerAdapter() {

    private var mImages: ArrayList<String> = ArrayList()

    init {
        mImages.addAll(images.values)
    }

    override fun isViewFromObject(p0: View?, p1: Any?): Boolean {
        return p0 == p1
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val photoView: PhotoView = PhotoView(container?.context)
        Glide.with(container?.context)
                .load(mImages[position])
                .asBitmap()
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(photoView)
        container?.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        return photoView
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container?.removeView(`object` as View)
    }

    override fun getCount() = mImages.size
    fun update(imagesMap: HashMap<String, String>) {
        mImages.clear()
        mImages.addAll(imagesMap.values)
        notifyDataSetChanged()
    }
}