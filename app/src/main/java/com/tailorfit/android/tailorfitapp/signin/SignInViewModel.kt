package com.tailorfit.android.tailorfitapp.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.models.request.SignInRequest
import com.tailorfit.android.tailorfitapp.models.response.SignUpResponse
import com.tailorfit.android.tailorfitapp.repositories.AccountsRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : BaseViewModel() {


    private val _signInResponse = MutableLiveData<SignUpResponse>()

    val signInResponse: LiveData<SignUpResponse>
        get() = _signInResponse

    fun signIn(signInRequest: SignInRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Signing up, please wait")
        accountsRepository.signIn(signInRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _signInResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value = LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)


    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(signInResponse)
    }

}