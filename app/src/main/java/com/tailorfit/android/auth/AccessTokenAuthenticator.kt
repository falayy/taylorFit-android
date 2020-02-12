
package com.tailorfit.android.auth

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AccessTokenAuthenticator(
    private val tokenProvider: AccessTokenProvider
) : Authenticator {

    companion object {
        const val AUTH_KEY = "access_token" // change to api_key, appid, auth_key etc, as required.
    }

    override fun authenticate(route: Route?, response: Response): Request? {
        // Get the token in sharedPref. Will be null for a new user
        val token = tokenProvider.token() ?: ""

        // Need to be thread-safe because of possibilities of concurrent requests authentication.
        synchronized(this) {
            // Get a new token
            val newToken = tokenProvider.refreshToken()

            // use the new token if the previous url already used the token in prefUtils
            val tokenToUse = if (response.request.url.queryParameter(AUTH_KEY) == token) newToken else token

            // remove any access token added before
            val urlBuilder = response.request.url.newBuilder().removeAllQueryParameters(AUTH_KEY)

            return response.request
                .newBuilder()
                .url(urlBuilder.addQueryParameter(AUTH_KEY, tokenToUse).build())
                .build()
        }
    }
}
