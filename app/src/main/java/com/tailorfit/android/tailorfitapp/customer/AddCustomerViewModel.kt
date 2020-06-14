/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.customer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.models.response.CreateCustomerResponse
import com.tailorfit.android.tailorfitapp.repositories.CustomerRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class AddCustomerViewModel @Inject constructor(
    private
    val customerRepository: CustomerRepository,
    private val prefsValueHelper: PrefsValueHelper
) : BaseViewModel() {

    private val _createCustomerResponse = MutableLiveData<CreateCustomerResponse>()

    val createCustomerResponse: LiveData<CreateCustomerResponse> = _createCustomerResponse

    fun createCustomer(token: String, createCustomerRequest: CreateCustomerRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Adding Customer, please wait")
        customerRepository.createCustomer(token, createCustomerRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        prefsValueHelper.setCustomerId(it.data.id)
                        _createCustomerResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value =
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(
            createCustomerResponse
        )
    }
}
