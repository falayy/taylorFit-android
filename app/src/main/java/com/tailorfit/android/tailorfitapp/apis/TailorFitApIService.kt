package com.tailorfit.android.tailorfitapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.tailorfitapp.models.request.*
import com.tailorfit.android.tailorfitapp.models.response.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface TailorFitApIService {

    @POST("/register")
    fun signUp(@Body body: SignUpRequest)
            : Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/login")
    fun signIn(@Body body: SignInRequest)
            : Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/customer/register")
    fun createCustomer(
        @Header("x-auth") token: String,
        @Body body: CreateCustomerRequest
    )
            : Single<Response<BaseAPIResponse<CreateCustomerResponse>>>

    @POST("/gigs/register")
    fun createGig(
        @Header("x-auth") token: String,
        @Body body: CreateGigRequest
    )
            : Single<Response<BaseAPIResponse<CreateGigResponse>>>

    @POST("/customer/measurement")
    fun createMeasurement(
        @Header("x-auth") token: String,
        @Body body: MeasurementRequest
    )
            : Single<Response<BaseAPIResponse<MeasurementResponse>>>

    @POST("/gig/done")
    fun addGigToDone(
        @Header("x-auth") token: String,
        @Body body: AddGigToDoneRequest
    )
            : Single<Response<BaseAPIResponse<AddToDoneResponse>>>

    @GET("/customer/gig/pending")
    fun getCustomersPendingJobsInfo(
        @Header("x-auth") token: String,
        @Header("user_id") userId: String
    )
            : Single<Response<BaseAPIResponse<List<CustomerInfoResponseModel>>>>

    @GET("/customer/gig/finish")
    fun getCustomersCompletedJobsInfo(
        @Header("x-auth") token: String,
        @Header("user_id") userId: String
    )
            : Single<Response<BaseAPIResponse<List<CustomerInfoResponseModel>>>>

    @GET("/measurement")
    fun getMeasurement(
        @Header("x-auth") token: String,
        @Header("customer_id") customerId: String,
        @Header("gig_id") gigId: String
    )
            : Single<Response<BaseAPIResponse<MeasurementResponse>>>


    @GET("/user/info")
    fun getUserInfo(
        @Header("x-auth") token: String
    )
            : Single<Response<BaseAPIResponse<UserInfoResponse>>>

}


