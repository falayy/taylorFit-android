
package com.tailorfit.android.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tailorfit.android.BuildConfig
import com.tailorfit.android.auth.AccessTokenAuthenticator
import com.tailorfit.android.auth.AccessTokenInterceptor
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
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [LocalDataModule::class])
class APIServiceModule {

    @Provides
    @Named("TailorFitApIService")
    @Singleton
    fun provideExampleServiceHttpClient(
        upstream: OkHttpClient,
        accessTokenProvider: AccessTokenProvider
    ): OkHttpClient {
        return upstream.newBuilder()
//            .addInterceptor(AccessTokenInterceptor(accessTokenProvider))
            .build()
    }

    //.authenticator(AccessTokenAuthenticator(accessTokenProvider))

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
        OkHttpClient.Builder().addInterceptor(interceptor).build()

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
