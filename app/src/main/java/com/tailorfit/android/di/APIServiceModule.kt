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
package com.tailorfit.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tailorfit.android.BuildConfig
import com.tailorfit.android.auth.AccessTokenProvider
import com.tailorfit.android.tailorfitapp.accesstoken.AccessTokenProviderImpl
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.tailorfitapp.apis.TailorFitApiAuthService
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [LocalDataModule::class])
class APIServiceModule {

    @Provides
    @Named("TailorFitApIService")
    @Singleton
    fun provideExampleServiceHttpClient(
        upstream: OkHttpClient
    ): OkHttpClient {
        return upstream.newBuilder()
            .build()
    }

    @Provides
    @Singleton
    fun provideTailorFitApiAuthService(
        client: Lazy<OkHttpClient>,
        gson: Gson
    ): TailorFitApiAuthService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client.get())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(TailorFitApiAuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideExampleAPIService(
        @Named("TailorFitApIService") client: Lazy<OkHttpClient>,
        gson: Gson
    ): TailorFitApIService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client.get())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .build()
            .create(TailorFitApIService::class.java)
    }

    @Provides
    fun provideAccessTokenProvider(accessTokenProvider: AccessTokenProviderImpl): AccessTokenProvider =
        accessTokenProvider

    @Provides
    @Singleton
    fun provideGenericOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().serializeNulls().create()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)
}
