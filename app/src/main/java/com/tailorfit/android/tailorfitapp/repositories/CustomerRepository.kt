package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.models.response.CreateCustomerResponse
import io.reactivex.Single
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val tailorFitApIService: TailorFitApIService
) {

    fun createCustomer(token : String, createCustomerRequest: CreateCustomerRequest):
            Single<Result<CreateCustomerResponse>> {
        return tailorFitApIService.createCustomer(token, createCustomerRequest).toResult()
    }
}