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
package com.tailorfit.android.tailorfitapp.accesstoken

import com.tailorfit.android.auth.AccessTokenProvider
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.repositories.OauthRepository
import javax.inject.Inject
import javax.inject.Singleton
import com.tailorfit.android.networkutils.Result

@Singleton
class AccessTokenProviderImpl @Inject constructor(
    private val oauthRepository: OauthRepository,
    private val prefsValueHelper: PrefsValueHelper
) : AccessTokenProvider {

    override fun token(): String? = prefsValueHelper.getAccessToken()

    override fun refreshToken(): String? {
        return when (val result = oauthRepository.getAccessToken()) {
            is Result.Success -> {
                prefsValueHelper.setAccessToken(result.data.accessToken)
                result.data.accessToken
            }
            is Result.Error -> null
        }
    }
}
