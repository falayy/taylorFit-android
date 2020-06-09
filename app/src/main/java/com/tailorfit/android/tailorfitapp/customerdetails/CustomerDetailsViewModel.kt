package com.tailorfit.android.tailorfitapp.customerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import com.tailorfit.android.tailorfitapp.models.response.AddToDoneResponse
import com.tailorfit.android.tailorfitapp.repositories.MeasurementRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CustomerDetailsViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository
) : BaseViewModel() {

    private var _addToDoneResponse = MutableLiveData<AddToDoneResponse>()

    val addToDoneResponse: LiveData<AddToDoneResponse>
        get() = _addToDoneResponse

    fun addGigToDone(token: String?, addGigToDoneRequest: AddGigToDoneRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Completing Jobs, Please Wait...")
        measurementRepository.markAsDone(token!!, addGigToDoneRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _addToDoneResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(loadingStatus, addToDoneResponse)
    }

}