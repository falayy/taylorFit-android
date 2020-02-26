package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.customer.AddCustomerFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerPhoneFragmentDirections
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts

enum class CustomerFormType {
    AddCustomerFragment,
    AddCustomerGenderFragment,
    AddCustomerPhoneFragment
}

abstract class BaseCustomerFormFragment : BaseFragment() {

    private lateinit var binding: FragmentBaseFormBinding
    private var data = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (validateTextLayouts(binding.editText)) {
            data = binding.editText.stringContent()
            navigate()
        }
    }

    private fun navigate() {
        when (getCustomerFormType()) {
            CustomerFormType.AddCustomerFragment -> {
                getCustomerRequest().name = data
                findNavController().navigate(AddCustomerFragmentDirections.
                    actionAddCustomerFragmentToAddCustomerPhoneFragment())
            }
            CustomerFormType.AddCustomerPhoneFragment -> {
                getCustomerRequest().phoneNumber = data
                findNavController().navigate(AddCustomerPhoneFragmentDirections.
                    actionAddCustomerPhoneFragmentToAddCustomerGenderFragment())
            }
            CustomerFormType.AddCustomerGenderFragment -> {
                getCustomerRequest().gender = data
                createCustomer()
            }
        }
    }

    private fun createCustomer() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    protected abstract fun getCustomerRequest(): CreateCustomerRequest

    protected abstract fun getCustomerFormType(): CustomerFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)


}

