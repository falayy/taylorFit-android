
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

    protected fun addAllLiveDataToObservablesList(vararg liveData: LiveData<*>) = observablesList.addAll(liveData)

    protected fun nullifyLiveDataValues(vararg liveDataValues: MutableLiveData<*>) {
        for (liveData in liveDataValues) liveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.clear()
    }
}
