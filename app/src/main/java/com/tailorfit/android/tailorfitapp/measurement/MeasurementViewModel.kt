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

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(measurementResponse, loadingStatus)
    }
}