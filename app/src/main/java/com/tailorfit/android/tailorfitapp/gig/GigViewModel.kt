package com.tailorfit.android.tailorfitapp.gig

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel
import com.tailorfit.android.tailorfitapp.repositories.GigsRepository
import timber.log.Timber
import javax.inject.Inject



enum class ImageUploadStatus { NOT_UPLOADED, UPLOADING, SUCCESS, FAILED }


class GigViewModel @Inject constructor(private val gigsRepository: GigsRepository) : BaseViewModel() {

    private val _imagePlaceHolder = MutableLiveData<List<GigImageModel>>()

    val imagePlaceHolder: LiveData<List<GigImageModel>>
        get() = _imagePlaceHolder

    private val _gigImageUploadStatus = MutableLiveData<ImageUploadStatus>(ImageUploadStatus.NOT_UPLOADED)
    val imageUploadStatus: LiveData<ImageUploadStatus>
        get() = _gigImageUploadStatus


    fun getImagePlaceHolders() {
        Timber.d("size is ${gigsRepository.getImagePlaceHolder().size}")
        _imagePlaceHolder.value = gigsRepository.getImagePlaceHolder()
    }

    fun uploadGigStyle(string: String,
                       sizedFileRequestBody: SizedFileRequestBody) {



    }



    override fun addAllLiveDataToObservablesList() {
    }



}