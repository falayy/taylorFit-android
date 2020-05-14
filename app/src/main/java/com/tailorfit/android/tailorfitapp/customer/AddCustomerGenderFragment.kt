package com.tailorfit.android.tailorfitapp.customer

import androidx.lifecycle.ViewModelProviders
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType


class AddCustomerGenderFragment : BaseCustomerFormFragment() {

    private lateinit var customerViewModel: AddCustomerViewModel

    override fun setUpDaggerViewModel() : AddCustomerViewModel {
        daggerAppComponent.inject(this)
        customerViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AddCustomerViewModel::class.java)
        return customerViewModel
    }

    override fun getViewModel(): BaseViewModel = customerViewModel


    override fun getCustomerFormType() = CustomerFormType.AddCustomerGenderFragment


    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_gender_message)
        binding.formValueInputLayout.hint = getString(R.string.gender)
        binding.editText.hint = getString(R.string.customer_gender_hint)
    }

}