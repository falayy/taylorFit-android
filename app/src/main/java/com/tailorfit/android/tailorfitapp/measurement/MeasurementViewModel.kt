package com.tailorfit.android.tailorfitapp.measurement

import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.FemaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.models.request.MaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.models.response.FemaleMeasurementResponse
import com.tailorfit.android.tailorfitapp.models.response.MaleMeasurementResponse
import com.tailorfit.android.tailorfitapp.repositories.MeasurementRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class MeasurementViewModel @Inject constructor(
    private val measurementRepository: MeasurementRepository
) :
    BaseViewModel() {

    private val _maleResponse = MutableLiveData<MaleMeasurementResponse>()

    val maleResponse = _maleResponse

    private val _femaleResponse = MutableLiveData<FemaleMeasurementResponse>()

    val femaleResponse = _femaleResponse


    fun createMaleMeasurement(
        token: String?,
        maleMeasurementRequest: MaleMeasurementRequest
    ) {

        _loadingStatus.value = LoadingStatus.Loading("Creating Measurement")
        measurementRepository.createMaleMeasurement(token!!, maleMeasurementRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _maleResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)


    }

    fun createFemaleMeasurement(
        token: String?,
        femaleMeasurementRequest: FemaleMeasurementRequest
    ) {

        _loadingStatus.value = LoadingStatus.Loading("Creating Measurement")
        measurementRepository.createFemaleMeasurement(token!!, femaleMeasurementRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _femaleResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)


    }

    fun getMaleMeasurement(token: String?, gigId: String?, customerId: String?) {
        _loadingStatus.value = LoadingStatus.Loading("Getting Measurements, Please Wait......")
        measurementRepository.getMaleMeasurement(token!!, gigId!!, customerId!!)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _maleResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)

    }

    fun getFemaleMeasurement(token: String?, gigId: String?, customerId: String?) {
        _loadingStatus.value = LoadingStatus.Loading("Getting Measurements, Please Wait......")
        measurementRepository.getFemaleMeasurement(token!!, gigId!!, customerId!!)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _femaleResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        _loadingStatus.value =
                            LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)

    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(maleResponse, femaleResponse, _femaleResponse, _maleResponse)
    }
}