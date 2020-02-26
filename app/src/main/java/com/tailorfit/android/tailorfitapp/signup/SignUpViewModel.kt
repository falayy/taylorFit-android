package com.tailorfit.android.tailorfitapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.models.response.SignUpResponse
import com.tailorfit.android.tailorfitapp.repositories.AccountsRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountsRepository
) : BaseViewModel() {


    private val _signUpResponse = MutableLiveData<SignUpResponse>()

    val signUpResponse: LiveData<SignUpResponse>
        get() = _signUpResponse

    fun signUp(signUpRequest: SignUpRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Signing up, please wait")
        accountRepository.signUp(signUpRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _signUpResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value = LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(signUpResponse)
    }
}
