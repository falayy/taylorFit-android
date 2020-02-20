package com.tailorfit.android.tailorfitapp.signin

import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.tailorfitapp.repositories.AccountsRepository
import javax.inject.Inject

class SignInViewModel @Inject constructor(
    private val accountsRepository: AccountsRepository
) : BaseViewModel(){
    override fun addAllLiveDataToObservablesList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}