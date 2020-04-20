package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel
import com.tailorfit.android.tailorfitapp.models.response.UserInfoResponse
import io.reactivex.Single
import javax.inject.Inject

class DashBoardDataRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun getUserInfo(token : String)
    : Single<Result<UserInfoResponse>> {
       return tailorFitApIService.getUserInfo(token).toResult()
    }

    fun getCustomersJobsInfo(token : String, userId : String)
    : Single<Result<List<CustomerInfoResponseModel>>> {
        return tailorFitApIService.getCustomersJobsInfo(token, userId).toResult()
    }

}