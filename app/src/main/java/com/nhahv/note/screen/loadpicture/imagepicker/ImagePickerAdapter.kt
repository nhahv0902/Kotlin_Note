package com.nhahv.note.screen.loadpicture.imagepicker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhahv.note.databinding.ItemImagePickerBinding

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class ImagePickerAdapter(viewModel: ImagePickerViewModel,
        imagePickers: ArrayList<String>?) : RecyclerView.Adapter<ImagePickerAdapter.ImagePickerHolder>() {

    var mImagePickers = imagePickers
    var mInflater: LayoutInflater? = null
    val mViewModel = viewModel

    fun update(imagePickers: ArrayList<String>) {
        mImagePickers?.clear()
        mImagePickers?.addAll(imagePickers)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImagePickerHolder?, position: Int) {
        val folder = mImagePickers?.get(position)
        folder?.let { holder?.bind(folder, position) }
    }

    override fun getItemCount(): Int {
        return mImagePickers?.size ?: 0
    }

    override fun onCreateViewHolder(view: ViewGroup?, position: Int): ImagePickerHolder {
        if (mInflater == null) mInflater = LayoutInflater.from(view?.context)
        val binding = ItemImagePickerBinding.inflate(mInflater, view, false)
        binding.viewModel = mViewModel
        return ImagePickerHolder(binding)
    }

    class ImagePickerHolder(binding: ItemImagePickerBinding) : RecyclerView.ViewHolder(
            binding.root) {
        val mBinding = binding

        fun bind(image: String, position: Int) {
            mBinding.image = image
            mBinding.position = position
            mBinding.executePendingBindings()
        }
    }
}
