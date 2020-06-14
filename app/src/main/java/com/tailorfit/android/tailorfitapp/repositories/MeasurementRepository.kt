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
package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import com.tailorfit.android.tailorfitapp.models.request.MeasurementRequest
import com.tailorfit.android.tailorfitapp.models.response.AddToDoneResponse
import com.tailorfit.android.tailorfitapp.models.response.MeasurementResponse
import io.reactivex.Single
import javax.inject.Inject

class MeasurementRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun createMeasurement(token: String, measurementRequest: MeasurementRequest):
            Single<Result<MeasurementResponse>> {
        return tailorFitApIService.createMeasurement(token, measurementRequest)
            .toResult()
    }

    fun getMeasurement(token: String, gigId: String, customerId: String):
            Single<Result<MeasurementResponse>> {
        return tailorFitApIService.getMeasurement(token, customerId, gigId)
            .toResult()
    }

    fun markAsDone(token: String, addGigToDoneRequest: AddGigToDoneRequest):
            Single<Result<AddToDoneResponse>> {
        return tailorFitApIService.addGigToDone(token, addGigToDoneRequest).toResult()
    }
}
