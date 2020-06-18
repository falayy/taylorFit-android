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
package com.tailorfit.android.tailorfitapp.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.SignInRequest
import com.tailorfit.android.tailorfitapp.models.response.SignUpResponse
import com.tailorfit.android.tailorfitapp.repositories.AccountsRepository
import com.tailorfit.android.tailorfitapp.signin.NavigationFlow.*
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

enum class NavigationFlow { NEW_USER, RETURNING_USER }

class SignInViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository,
    private val prefsValueHelper: PrefsValueHelper
) : BaseViewModel() {

    private val _signInResponse = MutableLiveData<SignUpResponse>()

    val signInResponse: LiveData<SignUpResponse>
        get() = _signInResponse

    private val _phoneNumber = MutableLiveData<String>()

    val phoneNumber: LiveData<String>
        get() = _phoneNumber

    private val _navigationFlow = MutableLiveData<NavigationFlow>()
    val navigationFlow: LiveData<NavigationFlow>
        get() = _navigationFlow

    init {
        if (prefsValueHelper.getUserPhoneNumber() != null) {
            _phoneNumber.value = prefsValueHelper.getUserPhoneNumber()
            _navigationFlow.value = RETURNING_USER
        } else {
            _navigationFlow.value = NEW_USER
        }
    }

    fun signIn(signInRequest: SignInRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Signing In, please wait...")
        accountsRepository.signIn(signInRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        prefsValueHelper.setAccessToken(it.data.token)
                        prefsValueHelper.setUserId(it.data.id)
                        _signInResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value =
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun cleanUpObservables() {
        nullifyLiveDataValues(_signInResponse, _navigationFlow, _phoneNumber, _loadingStatus)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(
            signInResponse,
            phoneNumber,
            navigationFlow,
            loadingStatus
        )
    }
}
