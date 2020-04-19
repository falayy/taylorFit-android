package com.tailorfit.android.tailorfitapp.customer


import androidx.lifecycle.ViewModelProviders
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType


class AddCustomerNameFragment : BaseCustomerFormFragment() {

    private lateinit var customerViewModel: AddCustomerViewModel

    override fun setUpDaggerViewModel()  : AddCustomerViewModel{
        daggerAppComponent.inject(this)
        customerViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AddCustomerViewModel::class.java)

        return  customerViewModel
    }

    override fun getViewModel(): BaseViewModel = customerViewModel

    override fun getCustomerFormType() = CustomerFormType.AddCustomerFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_name_message)
        binding.editText.hint = getString(R.string.customer_hint_name)
    }

}
