package com.tailorfit.android.tailorfitapp.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.models.response.CreateCustomerResponse
import com.tailorfit.android.tailorfitapp.repositories.CustomerRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class AddCustomerViewModel @Inject constructor(
    private
    val customerRepository: CustomerRepository
) : BaseViewModel() {

    private val _createCustomerResponse = MutableLiveData<CreateCustomerResponse>()

    val createCustomerResponse: LiveData<CreateCustomerResponse> = _createCustomerResponse


    fun createCustomer(token : String, createCustomerRequest: CreateCustomerRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Adding Customer, please wait")

        customerRepository.createCustomer(token, createCustomerRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _createCustomerResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success

                    }
                    is Result.Error -> _loadingStatus.value =
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(_createCustomerResponse)
    }
}