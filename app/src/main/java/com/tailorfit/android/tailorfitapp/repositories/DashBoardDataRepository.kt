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
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel
import com.tailorfit.android.tailorfitapp.models.response.UserInfoResponse
import io.reactivex.Single
import javax.inject.Inject

class DashBoardDataRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun getUserInfo(token: String):
            Single<Result<UserInfoResponse>> {
        return tailorFitApIService.getUserInfo(token).toResult()
    }

    fun getCustomersPendingJobsInfo(token: String, userId: String):
            Single<Result<List<CustomerInfoResponseModel>>> {
        return tailorFitApIService.getCustomersPendingJobsInfo(token, userId).toResult()
    }

    fun getCustomersCompletedJobsInfo(token: String, userId: String):
            Single<Result<List<CustomerInfoResponseModel>>> {
        return tailorFitApIService.getCustomersCompletedJobsInfo(token, userId).toResult()
    }
}
