package com.tailorfit.android.tailorfitapp.customer

import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.tailorfitapp.repositories.CustomerRepository
import javax.inject.Inject

class AddCustomerViewModel @Inject constructor(
    private
    val customerRepository: CustomerRepository
) : BaseViewModel() {







    override fun addAllLiveDataToObservablesList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}