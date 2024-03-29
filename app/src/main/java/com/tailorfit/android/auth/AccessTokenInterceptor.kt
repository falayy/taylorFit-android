/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
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
