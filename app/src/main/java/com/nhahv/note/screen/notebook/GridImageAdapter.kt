package com.nhahv.note.screen.notebook

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhahv.note.databinding.ItemGridImageBinding

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class GridImageAdapter(viewModel: NotebookViewModel,
    imagePickers: ArrayList<String>?) : RecyclerView.Adapter<GridImageAdapter.ImageViewHolder>() {

    var mImagePickers = imagePickers
    var mInflater: LayoutInflater? = null
    val mViewModel = viewModel

    fun update(imagePickers: ArrayList<String>) {
        mImagePickers?.clear()
        mImagePickers?.addAll(imagePickers)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageViewHolder?, position: Int) {
        val image = mImagePickers?.get(position)
        image?.let { holder?.bind(image, position) }
    }

    override fun getItemCount(): Int {
        return mImagePickers?.size ?: 0
    }

    override fun onCreateViewHolder(view: ViewGroup?, position: Int): ImageViewHolder {
        if (mInflater == null) mInflater = LayoutInflater.from(view?.context)
        val binding = ItemGridImageBinding.inflate(mInflater, view, false)
        binding.viewModel = mViewModel
        return ImageViewHolder(binding)
    }

    class ImageViewHolder(binding: ItemGridImageBinding) : RecyclerView.ViewHolder(
        binding.root) {
        val mBinding = binding

        fun bind(image: String, position: Int) {
            mBinding.image = image
            mBinding.position = position
            mBinding.executePendingBindings()
        }
    }
}
