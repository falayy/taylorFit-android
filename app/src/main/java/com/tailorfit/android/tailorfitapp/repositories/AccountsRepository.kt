package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.models.request.SignUpRespone
import io.reactivex.Single
import javax.inject.Inject

class AccountsRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    fun signUp(body: SignUpRequest): Single<Result<SignUpRespone>> {
        return tailorFitApIService.signUp(body).toResult()
    }
}