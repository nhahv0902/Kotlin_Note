package com.nhahv.note.screen.loadpicture.folder

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhahv.note.databinding.ItemImageFolderBinding
import com.nhahv.note.screen.loadpicture.folder.AlbumAdapter.AlbumHolder
import com.nhahv.note.screen.loadpicture.model.Folder

/**
 * Created by Hoang Van Nha on 5/28/2017.
 * <>
 */

class AlbumAdapter(viewModel: FolderViewModel,
        folders: ArrayList<Folder>?) : RecyclerView.Adapter<AlbumHolder>() {

    var mFolders = folders
    var mInflater: LayoutInflater? = null
    val mViewModel = viewModel

    fun update(folders: ArrayList<Folder>) {
        mFolders?.clear()
        mFolders?.addAll(folders)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: AlbumHolder?, position: Int) {
        val folder = mFolders?.get(position)
        folder?.let { holder?.bind(folder, position) }
    }

    override fun getItemCount(): Int {
        return mFolders?.size ?: 0
    }

    override fun onCreateViewHolder(view: ViewGroup?, position: Int): AlbumHolder {
        if (mInflater == null) mInflater = LayoutInflater.from(view?.context)
        val binding = ItemImageFolderBinding.inflate(mInflater, view, false)
        binding.viewModel = mViewModel
        return AlbumHolder(binding)
    }

    class AlbumHolder(binding: ItemImageFolderBinding) : RecyclerView.ViewHolder(binding.root) {
        val mBinding = binding

        fun bind(folder: Folder, position: Int) {
            mBinding.folder = folder
            mBinding.position = position
            mBinding.executePendingBindings()
        }
    }
}
