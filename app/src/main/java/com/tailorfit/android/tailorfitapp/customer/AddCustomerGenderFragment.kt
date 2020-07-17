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
package com.tailorfit.android.tailorfitapp.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentAddCustomerGenderBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateDropdownViews
import javax.inject.Inject

class AddCustomerGenderFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var binding: FragmentAddCustomerGenderBinding
    private lateinit var customerViewModel: AddCustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCustomerGenderBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        customerViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddCustomerViewModel::class.java)
        customerViewModel.getGender()
        binding.formDescription.text = getString(R.string.customer_gender_message)
        binding.formValueInputLayout.hint = getString(R.string.gender)

        customerViewModel.
            genderMenuList.observe(viewLifecycleOwner, Observer {
                binding.genderDropdown.setAdapter(
                    ArrayAdapter(
                        binding.genderDropdown.context,
                        R.layout.dropdown_menu_item,
                        it
                    )
                )
            })




        binding.FormProceedButton.setOnClickListener {
            if (
                validateDropdownViews(binding.genderDropdown)
            ) {
                prefsValueHelper.setCustomerGender(binding.genderDropdown.getSelectedItemKey())
                val args = AddCustomerGenderFragmentArgs.fromBundle(arguments!!)
                customerViewModel.createCustomer(
                    CreateCustomerRequest(
                        binding.genderDropdown.getSelectedItemKey(),
                        args.createCustomer.name,
                        args.createCustomer.phoneNumber,
                        prefsValueHelper.getUserId()
                    )
                )
                customerViewModel.apply {
                    createCustomerResponse.observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            findNavController().navigate(
                                AddCustomerGenderFragmentDirections
                                    .actionAddCustomerGenderFragmentToAddGigTitleFragment()
                            )
                        }
                    })
                    cleanUpObservables()
                }
            } else {
                return@setOnClickListener
            }
        }
    }

    override fun getViewModel(): BaseViewModel = customerViewModel


}
