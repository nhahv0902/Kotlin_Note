package com.nhahv.note.screen.notecreation

import com.google.android.gms.maps.model.LatLng
import com.nhahv.note.data.model.Notebook
import com.nhahv.note.data.source.address.AddressDataSource
import com.nhahv.note.data.source.address.AddressRepository
import com.nhahv.note.data.source.creation.NotebookDataSource
import com.nhahv.note.data.source.creation.NotebookRepository
import com.nhahv.note.data.source.picture.PictureStorageRepository

/**
 * Created by Hoang Van Nha on 5/29/2017.
 * <>
 */
class NoteCreationPresenter(
        viewModel: NoteCreationContract.ViewModel) : NoteCreationContract.Presenter {

    val mViewModel: NoteCreationContract.ViewModel = viewModel
    val mNotebookRepository = NotebookRepository()
    private val mUploadPictureRepository = PictureStorageRepository()
    private val mAddressRepository = AddressRepository()


    override fun onStart() {

    }

    override fun onStop() {
    }

    override fun addNotebook(notebook: Notebook, callback: NotebookDataSource.Callback) {
        mNotebookRepository.addNotebook(notebook, callback)
    }

    override fun upPicture(pathPicture: String) {

    }

    override fun getAddress(latLng: LatLng) {
        mAddressRepository.getAddress(latLng, object : AddressDataSource.AddressCallback {
            override fun onLoadAddressed(address: String) {
                mViewModel.onGetAddressSuccess(address)
            }

            override fun onLoadAddressFault() {
                mViewModel.onGetAddressError()
            }
        })
    }
}