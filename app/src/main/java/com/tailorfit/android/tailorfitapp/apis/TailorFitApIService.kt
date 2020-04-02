package com.tailorfit.android.tailorfitapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.models.request.SignInRequest
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.models.response.CreateCustomerResponse
import com.tailorfit.android.tailorfitapp.models.response.CreateGigResponse
import com.tailorfit.android.tailorfitapp.models.response.SignUpResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface TailorFitApIService {

    @POST("/register")
    fun signUp(
        @Body body: SignUpRequest
    ): Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/login")
    fun signIn(
        @Body body: SignInRequest
    ): Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/customer/register")
    fun createCustomer(
        @Header("x-auth") token: String,
        @Body body: CreateCustomerRequest):
            Single<Response<BaseAPIResponse<CreateCustomerResponse>>>

    @POST("/gigs/register")
    fun createGig(
        @Header("x-auth") token: String,
        @Body body: CreateGigRequest
    ): Single<Response<BaseAPIResponse<CreateGigResponse>>>
}


