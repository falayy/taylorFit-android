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
package com.tailorfit.android.tailorfitapp.measurement

import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.MeasurementRequest
import com.tailorfit.android.tailorfitapp.models.response.MeasurementMapperModel
import com.tailorfit.android.tailorfitapp.models.response.MeasurementResponse
import com.tailorfit.android.tailorfitapp.repositories.MeasurementRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MeasurementViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository
) :
    BaseViewModel() {

    private val _measurementResponse = MutableLiveData<List<MeasurementMapperModel>>()
    val measurementResponse = _measurementResponse

    private val _createMeasurementResponse = MutableLiveData<MeasurementResponse>()
    val createMeasurementResponse = _createMeasurementResponse

    fun createMeasurement(
        token: String?,
        measurementRequest: MeasurementRequest
    ) {

        _loadingStatus.value = LoadingStatus.Loading("Creating Measurement")
        measurementRepository.createMeasurement(token!!, measurementRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _createMeasurementResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)
    }

    fun getMeasurements(token: String?, gigId: String?, customerId: String?) {
        _loadingStatus.value = LoadingStatus.Loading("Getting Measurements, Please Wait......")
        measurementRepository.getMeasurement(token!!, gigId!!, customerId!!)
            .subscribeBy { result ->
                when (result) {
                    is Result.Success -> {
                        val measurementList = mutableListOf<MeasurementMapperModel>()
                        result.data.measurement.forEach {
                            measurementList.add(
                                MeasurementMapperModel(
                                    it.key,
                                    it.value
                                )
                            )
                        }
                        _measurementResponse.value = measurementList
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(result.errorCode, result.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)
    }

    override fun cleanUpObservables() {
        nullifyLiveDataValues(_loadingStatus, _createMeasurementResponse, _measurementResponse)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(measurementResponse, loadingStatus)
    }
}
