package com.tailorfit.android.base

import androidx.lifecycle.Observer
import com.tailorfit.android.networkutils.LoadingStatus
import timber.log.Timber

abstract class BaseViewModelFragment : BaseFragment() {

    override fun onStart() {
        super.onStart()
        if (getViewModel().loadingStatus.hasObservers()) return
        getViewModel().loadingStatus.observe(this, Observer {
            when (it) {
                LoadingStatus.Success -> mainActivity.dismissLoading()
                is LoadingStatus.Loading -> mainActivity.showLoading(it.message)
                is LoadingStatus.Error -> {
                    Timber.d("I, \"mainActivity.showError\", can testify that I have been called")
                    mainActivity.showError(it.errorMessage)
                    getViewModel().errorShown()
                }
            }
        })
    }
    //showLoading(it.message)

    override fun onDestroyView() {
        super.onDestroyView()
        getViewModel().addAllLiveDataToObservablesList()
        for (liveData in getViewModel().observablesList) liveData.removeObservers(this)
    }

    abstract fun getViewModel(): BaseViewModel
}
