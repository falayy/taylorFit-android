/**
 * Copyright (c) 2019 Cotta & Cush Limited.
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

    override fun onDestroyView() {
        super.onDestroyView()
        getViewModel().addAllLiveDataToObservablesList()
        for (liveData in getViewModel().observablesList) liveData.removeObservers(this)
    }

    abstract fun getViewModel(): BaseViewModel
}
