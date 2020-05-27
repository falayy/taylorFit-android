package com.tailorfit.android.tailorfitapp.customerdetails

import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import com.tailorfit.android.tailorfitapp.repositories.MeasurementRepository
import javax.inject.Inject

class CustomerDetailsViewModel @Inject constructor(private val measurementRepository: MeasurementRepository) :
    BaseViewModel() {


    fun addGigToDone(token: String?, addGigToDoneRequest: AddGigToDoneRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Completing Jobs, Please Wait......")
        measurementRepository.markAsDone(token!!, addGigToDoneRequest)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(loadingStatus)
    }

}