package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.customer.AddCustomerGenderFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerNameFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerPhoneFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerViewModel
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

enum class CustomerFormType {
    AddCustomerFragment,
    AddCustomerGenderFragment,
    AddCustomerPhoneFragment
}

abstract class BaseCustomerFormFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var binding: FragmentBaseFormBinding
    private lateinit var createCustomerRequest: CreateCustomerRequest
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
        setDataHints(binding)
        setUpDaggerViewModel()
        navigate()
    }

    private fun navigate() {
        when (getCustomerFormType()) {
            CustomerFormType.AddCustomerFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val name = binding.editText.stringContent()
                        prefsValueHelper.setCustomerName(name)
                        findNavController().navigate(
                            AddCustomerNameFragmentDirections.actionAddCustomerNameFragmentToAddCustomerPhoneFragment()
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerPhoneFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val phoneNumber = binding.editText.stringContent()
                        prefsValueHelper.setCustomerPhone(phoneNumber)
                        findNavController().navigate(
                            AddCustomerPhoneFragmentDirections.actionAddCustomerPhoneFragmentToAddCustomerGenderFragment()
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerGenderFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        data = binding.editText.stringContent()
                        createCustomerRequest = CreateCustomerRequest(
                            data,
                            prefsValueHelper.getCustomerName(),
                            prefsValueHelper.getCustomerPhone(),
                            prefsValueHelper.getUserId()
                        )
                        createCustomer()
                    } else {
                        return@setOnClickListener
                    }
                }
            }
        }
    }

    private fun createCustomer() {
        setUpDaggerViewModel().createCustomer(
            prefsValueHelper.getAccessToken().toString(),
            createCustomerRequest
        )
        setUpDaggerViewModel().createCustomerResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(AddCustomerGenderFragmentDirections.actionAddCustomerGenderFragmentToAddGigTitleFragment())
            }
        })
    }


    protected abstract fun getCustomerFormType(): CustomerFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)

    protected abstract fun setUpDaggerViewModel(): AddCustomerViewModel


}

