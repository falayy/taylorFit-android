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
package com.tailorfit.android.tailorfitapp.userdashboard

import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel
import com.tailorfit.android.tailorfitapp.models.response.UserInfoResponse
import com.tailorfit.android.tailorfitapp.repositories.DashBoardDataRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class DashBoardViewModel @Inject constructor(
    private val dashBoardDataRepository: DashBoardDataRepository
) : BaseViewModel() {

    private var _userInfoResponse = MutableLiveData<UserInfoResponse>()
    val userInfoResponse = _userInfoResponse

    private var _pendingCustomerJobsInfoResponse =
        MutableLiveData<List<CustomerInfoResponseModel>?>()
    val pendingCustomerInfoResponse = _pendingCustomerJobsInfoResponse

    private var _completedCustomerJobsInfoResponse =
        MutableLiveData<List<CustomerInfoResponseModel>?>()
    val completedCustomerInfoResponse = _completedCustomerJobsInfoResponse

    fun userInfo(
        token: String?
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait")
        dashBoardDataRepository.getUserInfo(token!!)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _userInfoResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> {
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                    }
                }
            }.disposeBy(disposeBag)
    }

    fun getCustomerPendingJobsInfo(
        token: String?,
        userId: String?
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait...")
        dashBoardDataRepository.getCustomersPendingJobsInfo(
            token!!, userId!!
        ).subscribeBy {
            when (it) {
                is Result.Success -> {
                    _pendingCustomerJobsInfoResponse.value = it.data
                        .sortedBy { fetchCustomerInfoResponse ->
                            fetchCustomerInfoResponse.isDone == false
                        }
                }
                is Result.Error -> {
                    LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }
        }.disposeBy(disposeBag)
    }

    fun getCustomerCompletedJobsInfo(
        token: String?,
        userId: String?
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait...")
        dashBoardDataRepository.getCustomersCompletedJobsInfo(
            token!!, userId!!
        ).subscribeBy {
            when (it) {
                is Result.Success -> {
                    _completedCustomerJobsInfoResponse.value = it.data
                        .sortedBy { fetchCustomerInfoResponse ->
                            fetchCustomerInfoResponse.isDone == true
                        }
                    _loadingStatus.value = LoadingStatus.Success
                }
                is Result.Error -> {
                    LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }
        }.disposeBy(disposeBag)
    }

    override fun cleanUpObservables() {
        nullifyLiveDataValues(
            _loadingStatus,
            _userInfoResponse,
            _completedCustomerJobsInfoResponse,
            _pendingCustomerJobsInfoResponse
        )
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(
            _userInfoResponse,
            userInfoResponse,
            completedCustomerInfoResponse,
            pendingCustomerInfoResponse
        )
    }
}
