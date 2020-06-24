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
package com.tailorfit.android.tailorfitapp.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.models.response.SignUpResponse
import com.tailorfit.android.tailorfitapp.repositories.AccountsRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val accountRepository: AccountsRepository,
    private val prefsValueHelper: PrefsValueHelper
) : BaseViewModel() {

    private val _signUpResponse = MutableLiveData<SignUpResponse>()

    val signUpResponse: LiveData<SignUpResponse>
        get() = _signUpResponse

    fun signUp(signUpRequest: SignUpRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Signing up, please wait...")
        prefsValueHelper.setUserPhoneNumber(signUpRequest.phoneNumber)
        accountRepository.signUp(signUpRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        prefsValueHelper.setAccessToken(it.data.token)
                        prefsValueHelper.setUserId(it.data.id)
                        _signUpResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value =
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun cleanUpObservables() {
        nullifyLiveDataValues(_loadingStatus, _signUpResponse)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(
            signUpResponse,
            loadingStatus
        )
    }
}
