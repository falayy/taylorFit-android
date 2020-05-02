package com.tailorfit.android.tailorfitapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.tailorfitapp.models.request.*
import com.tailorfit.android.tailorfitapp.models.response.*
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TailorFitApIService {

    @POST("/register")
    fun signUp(@Body body: SignUpRequest): Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/login")
    fun signIn(@Body body: SignInRequest): Single<Response<BaseAPIResponse<SignUpResponse>>>

    @POST("/customer/register")
    fun createCustomer(
        @Header("x-auth") token: String,
        @Body body: CreateCustomerRequest
    ):
            Single<Response<BaseAPIResponse<CreateCustomerResponse>>>

    @POST("/gigs/register")
    fun createGig(
        @Header("x-auth") token: String,
        @Body body: CreateGigRequest
    ): Single<Response<BaseAPIResponse<CreateGigResponse>>>

    @POST("/customer/measurement/male")
    fun createMaleMeasurement(
        @Header("x-auth") token: String,
        @Body body: MaleMeasurementRequest
    )
            : Single<Response<BaseAPIResponse<MaleMeasurementResponse>>>

    @POST("/customer/measurement/female")
    fun createFemaleMeasurement(
        @Header("x-auth") token: String,
        @Body body: FemaleMeasurementRequest
    )
            : Single<Response<BaseAPIResponse<FemaleMeasurementResponse>>>

    @POST("/gig/done")
    fun addGigToDone(
        @Header("x-auth") token: String,
        @Body body: AddGigToDoneRequest
    )

    @GET("/customer/gig")
    fun getCustomersJobsInfo(
        @Header("x-auth") token: String,
        @Header("user_id") userId: String
    ): Single<Response<BaseAPIResponse<List<CustomerInfoResponseModel>>>>

    @GET("")
    fun getCustomeMaleMeasurement(
        @Header("x-auth") token: String,
        @Header("customer_id") customerId: String,
        @Header("gig_id") gigId: String
    )
//            : Single<Response<BaseAPIResponse<>>>

    @GET("")
    fun getCustomeFemaleMeasurement(
        @Header("x-auth") token: String,
        @Header("customer_id") customerId: String,
        @Header("gig_id") gigId: String
    )
//            : Single<Response<BaseAPIResponse<>>>


    @GET("/user/info")
    fun getUserInfo(
        @Header("x-auth") token: String
    ): Single<Response<BaseAPIResponse<UserInfoResponse>>>

}


