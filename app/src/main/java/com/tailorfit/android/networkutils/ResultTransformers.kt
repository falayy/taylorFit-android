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
package com.tailorfit.android.networkutils

import androidx.annotation.CheckResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.google.android.gms.tasks.Task
import com.tailorfit.android.R
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

@CheckResult
fun <R> Task<R>.toSingle() = Single.create<R> { emitter ->
    addOnSuccessListener { emitter.onSuccess(it) }
        .addOnFailureListener { emitter.onError(it) }
}

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun Disposable.disposeBy(compositeDisposable: CompositeDisposable) = apply { compositeDisposable.add(this) }
