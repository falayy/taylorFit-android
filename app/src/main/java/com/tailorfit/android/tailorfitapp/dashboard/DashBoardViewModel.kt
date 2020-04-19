package com.tailorfit.android.tailorfitapp.dashboard

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

    private var _customerJobsInfoResponse = MutableLiveData<List<CustomerInfoResponseModel>>()
    val customerInfoResponse = _customerJobsInfoResponse


    fun userInfo(
        token: String
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait")
        dashBoardDataRepository.getUserInfo(token)
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
        token: String,
        userId: String
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait")
        dashBoardDataRepository.getCustomersJobsInfo(
            token, userId
        ).subscribeBy {
            when (it) {
                is Result.Success -> {
                    _customerJobsInfoResponse.value = it.data
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
        token: String,
        userId: String
    ) {
        _loadingStatus.value = LoadingStatus.Loading("Loading DashBoard, Please Wait")
        dashBoardDataRepository.getCustomersJobsInfo(
            token, userId
        ).subscribeBy {
            when (it) {
                is Result.Success -> {
                    _customerJobsInfoResponse.value = it.data
                        .sortedBy { fetchCustomerInfoResponse ->
                            fetchCustomerInfoResponse.isDone == true
                        }
                }
                is Result.Error -> {
                    LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }
        }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}