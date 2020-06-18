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
package com.tailorfit.android.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    protected val disposeBag = CompositeDisposable()

    val observablesList: MutableList<LiveData<*>> = mutableListOf()

    protected val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus

    fun errorShown() {
        _loadingStatus.value = null
    }

    /**
     * We want to add all observables to the list so that they are removed when the view is destroyed
     * If this isn't done, we would have multiple observers on the same variable which might lead to a crash
     */
    abstract fun addAllLiveDataToObservablesList()

    abstract fun cleanUpObservables()

    protected fun addAllLiveDataToObservablesList(vararg liveData: LiveData<*>) = observablesList.addAll(liveData)

    protected fun nullifyLiveDataValues(vararg liveDataValues: MutableLiveData<*>) {
        for (liveData in liveDataValues) liveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
