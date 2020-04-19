package com.tailorfit.android.tailorfitapp.accesstoken

import com.tailorfit.android.auth.AccessTokenProvider
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.repositories.OauthRepository
import javax.inject.Inject
import javax.inject.Singleton

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
