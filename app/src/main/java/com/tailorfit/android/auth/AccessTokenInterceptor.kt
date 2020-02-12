
package com.tailorfit.android.auth

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class AccessTokenInterceptor(
    private val tokenProvider: AccessTokenProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Timber.d("Should intercept token ")
        val token = tokenProvider.token()
        return if (token == null) {
            chain.proceed(chain.request())
        } else {
            val requestBuilder = chain.request().newBuilder()
            val url = chain.request().url.newBuilder()
                .addQueryParameter(AccessTokenAuthenticator.AUTH_KEY, token).build()
            requestBuilder.url(url)
            return chain.proceed(requestBuilder.build())
        }
    }
}
