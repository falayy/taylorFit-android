package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.customer.AddCustomerGenderFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerNameFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerPhoneFragmentDirections
import com.tailorfit.android.tailorfitapp.customer.AddCustomerViewModel
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import timber.log.Timber
import javax.inject.Inject

enum class CustomerFormType {
    AddCustomerFragment,
    AddCustomerGenderFragment,
    AddCustomerPhoneFragment
}

abstract class BaseCustomerFormFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: FragmentBaseFormBinding
    private lateinit var createCustomerRequest: CreateCustomerRequest
    private var data = ""
    private lateinit var customerViewModel: AddCustomerViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        daggerAppComponent.inject(this)
//        customerViewModel = ViewModelProviders.of(this,
//            viewModelFactory).get(AddCustomerViewModel::class.java)
        setDataHints(binding)
        navigate()
    }

    private fun navigate() {
        var name = ""
        var phoneNumber = ""
        when (getCustomerFormType()) {
            CustomerFormType.AddCustomerFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (!validateTextLayouts(binding.editText)) {
                        name = binding.editText.stringContent()
                    }
                    findNavController().navigate(
                        AddCustomerNameFragmentDirections.actionAddCustomerNameFragmentToAddCustomerPhoneFragment()
                    )
                }
            }
            CustomerFormType.AddCustomerPhoneFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (!validateTextLayouts(binding.editText)) {
                        phoneNumber = binding.editText.stringContent()
                    }
                    findNavController().navigate(
                        AddCustomerPhoneFragmentDirections.actionAddCustomerPhoneFragmentToAddCustomerGenderFragment()
                    )
                }
            }
            CustomerFormType.AddCustomerGenderFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (!validateTextLayouts(binding.editText)) {
                        data = binding.editText.stringContent()
                    }
                    createCustomerRequest = CreateCustomerRequest(
                        data,
                        name,
                        phoneNumber,
                        "kfj"
                    )
                    createCustomer()
                }
            }
        }
    }

    private fun createCustomer() {
        Toast.makeText(mainActivity, "Handle me!", Toast.LENGTH_SHORT).show()
        findNavController().navigate(AddCustomerGenderFragmentDirections.actionAddCustomerGenderFragmentToAddGigTitleFragment())
    }


    protected abstract fun getCustomerFormType(): CustomerFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)

//    override fun getViewModel(): BaseViewModel = customerViewModel


}

