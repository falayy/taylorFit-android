package com.tailorfit.android.networkutils

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher
import retrofit2.Response

// Still not sure of what to name this file as of now 🤔

fun <T : Any> Single<Response<BaseAPIResponse<T>>>.toResult(): Single<Result<T>> {
    return compose { item ->
        item
            .map { getAPIResult(it) }
            .onErrorReturn { e -> Result.Error(GENERIC_ERROR_CODE, e.message ?: GENERIC_ERROR_MESSAGE) }
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun Disposable.disposeBy(compositeDisposable: CompositeDisposable) = apply { compositeDisposable.add(this) }
