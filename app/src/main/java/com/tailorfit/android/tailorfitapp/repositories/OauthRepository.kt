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
package com.tailorfit.android.tailorfitapp.repositories

import com.tailorfit.android.BuildConfig
import com.tailorfit.android.tailorfitapp.models.request.AccessTokenRequest
import com.tailorfit.android.tailorfitapp.models.response.AccessToken
import com.tailorfit.android.networkutils.GENERIC_ERROR_CODE
import com.tailorfit.android.networkutils.GENERIC_ERROR_MESSAGE
import com.tailorfit.android.networkutils.getAPIResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.tailorfitapp.apis.TailorFitApiAuthService

import javax.inject.Inject

class OauthRepository @Inject constructor(private val oauthService: TailorFitApiAuthService) {

    fun getAccessToken(): Result<AccessToken> {
        return try {
            getAPIResult(oauthService.getAccessToken(getTokenRequest()).execute())
        } catch (e: Exception) {
            Result.Error(GENERIC_ERROR_CODE, e.message ?: GENERIC_ERROR_MESSAGE)
        }
    }

    private fun getTokenRequest() = AccessTokenRequest(
        BuildConfig.OAUTH_GRANT_TYPE, BuildConfig.OAUTH_CLIENT_ID, BuildConfig.OAUTH_CLIENT_SECRET
    )
}
