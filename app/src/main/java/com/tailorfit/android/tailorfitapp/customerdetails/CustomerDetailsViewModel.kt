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
