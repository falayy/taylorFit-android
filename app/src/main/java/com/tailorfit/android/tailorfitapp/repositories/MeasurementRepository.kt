package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import com.tailorfit.android.tailorfitapp.models.request.FemaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.models.request.MaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.models.response.FemaleMeasurementResponse
import com.tailorfit.android.tailorfitapp.models.response.MaleMeasurementResponse
import io.reactivex.Single
import javax.inject.Inject

class MeasurementRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun createMaleMeasurement(token: String, maleMeasurementRequest: MaleMeasurementRequest)
            : Single<Result<MaleMeasurementResponse>> {
        return tailorFitApIService.createMaleMeasurement(token, maleMeasurementRequest).toResult()
    }

    fun createFemaleMeasurement(token: String, femaleMeasurementRequest: FemaleMeasurementRequest)
            : Single<Result<FemaleMeasurementResponse>> {
        return tailorFitApIService.createFemaleMeasurement(token, femaleMeasurementRequest)
            .toResult()
    }

    fun getMaleMeasurement(token: String, gigId : String, customerId : String)
            : Single<Result<MaleMeasurementResponse>> {
          return tailorFitApIService.getCustomeMaleMeasurement(token, customerId, gigId)
              .toResult()
    }

    fun getFemaleMeasurement(token: String, gigId : String, customerId : String)
            : Single<Result<FemaleMeasurementResponse>> {
           return tailorFitApIService.getCustomeFemaleMeasurement(token, customerId, gigId)
               .toResult()
    }


    fun markAsDone(token: String, addGigToDoneRequest: AddGigToDoneRequest) {
        tailorFitApIService.addGigToDone(token, addGigToDoneRequest)
    }

}