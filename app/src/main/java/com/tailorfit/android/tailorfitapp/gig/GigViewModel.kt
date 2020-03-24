package com.tailorfit.android.tailorfitapp.gig

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel
import com.tailorfit.android.tailorfitapp.repositories.GigsRepository
import timber.log.Timber
import javax.inject.Inject

class GigViewModel @Inject constructor(private val gigsRepository: GigsRepository) : BaseViewModel() {

    private val _imagePlaceHolder = MutableLiveData<List<GigImageModel>>()

    val imagePlaceHolder: LiveData<List<GigImageModel>>
        get() = _imagePlaceHolder


    fun getImagePlaceHolders() {
        Timber.d("size is ${gigsRepository.getImagePlaceHolder().size}")
        Log.d("TAG", "size in vm ${gigsRepository.getImagePlaceHolder().size}")
        _imagePlaceHolder.value = gigsRepository.getImagePlaceHolder()
    }

    override fun addAllLiveDataToObservablesList() {
    }

}