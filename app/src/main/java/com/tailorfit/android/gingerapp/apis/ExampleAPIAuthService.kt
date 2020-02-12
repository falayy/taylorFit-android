/**
 * Copyright (c) 2019 Cotta & Cush Limited.
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
package com.tailorfit.android.gingerapp.apis

import com.tailorfit.android.networkutils.BaseAPIResponse
import com.tailorfit.android.gingerapp.models.request.AccessTokenRequest
import com.tailorfit.android.gingerapp.models.response.AccessToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ExampleAPIAuthService {

    @POST("/oauth/token")
    fun getAccessToken(@Body body: AccessTokenRequest): Call<BaseAPIResponse<AccessToken>>
}
