package com.tailorfit.android.tailorfitapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.tailorfitapp.models.request.AccessTokenRequest
import com.tailorfit.android.tailorfitapp.models.response.AccessToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface TailorFitApiAuthService  {

    @POST("/oauth/token")
    fun getAccessToken(@Body body: AccessTokenRequest): Call<BaseAPIResponse<AccessToken>>
}