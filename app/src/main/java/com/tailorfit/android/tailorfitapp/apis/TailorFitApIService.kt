package com.tailorfit.android.tailorfitapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.models.request.SignUpRespone
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TailorFitApIService {

    @POST("/register")
    fun signUp(@Body body: SignUpRequest): Single<Response<BaseAPIResponse<SignUpRespone>>>
}


