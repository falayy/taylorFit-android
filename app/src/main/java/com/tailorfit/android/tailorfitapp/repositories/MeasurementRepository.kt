package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import com.tailorfit.android.tailorfitapp.models.request.MeasurementRequest
import com.tailorfit.android.tailorfitapp.models.response.MeasurementResponse
import io.reactivex.Single
import javax.inject.Inject

class MeasurementRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun createMeasurement(token: String, measurementRequest: MeasurementRequest)
            : Single<Result<MeasurementResponse>> {
        return tailorFitApIService.createFemaleMeasurement(token, measurementRequest)
            .toResult()
    }

    fun getMeasurement(token: String, gigId: String, customerId: String)
            : Single<Result<MeasurementResponse>> {
        return tailorFitApIService.getMeasurement(token, customerId, gigId)
            .toResult()
    }


    fun markAsDone(token: String, addGigToDoneRequest: AddGigToDoneRequest) {
        tailorFitApIService.addGigToDone(token, addGigToDoneRequest)
    }

}