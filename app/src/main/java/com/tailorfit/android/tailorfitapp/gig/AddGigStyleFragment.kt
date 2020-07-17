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
package com.tailorfit.android.tailorfitapp.gig

import android.os.Bundle
import android.util.Log
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
import com.tailorfit.android.databinding.FragmentAddGigStyleBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.validateDropdownViews
import javax.inject.Inject

class AddGigStyleFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentAddGigStyleBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var viewModel: GigViewModel

    private lateinit var createGigRequest: CreateGigRequest


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddGigStyleBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        val args = AddGigStyleFragmentArgs.fromBundle(arguments!!)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GigViewModel::class.java)
        viewModel.getStyleList(prefsValueHelper.getCustomerGender()!!)
        binding.formDescription.text = getString(R.string.gig_style_message)
        binding.formValueInputLayout.hint = getString(R.string.style)

        viewModel.styleMenuList.observe(viewLifecycleOwner, Observer {
            binding.styleDropdown.setAdapter(
                ArrayAdapter(
                    binding.styleDropdown.context,
                    R.layout.dropdown_menu_item,
                    it
                )
            )
        })

        binding.FormProceedButton.setOnClickListener {
            if (
                validateDropdownViews(binding.styleDropdown)
            ) {
                createGigRequest = CreateGigRequest()
                createGigRequest.title = args.createGig.title
                createGigRequest.styleName = binding.styleDropdown.getSelectedItemKey()

                findNavController().navigate(
                    AddGigStyleFragmentDirections.actionAddGigStyleFragmentToAddGigDueDateFragment(
                        createGigRequest
                    )
                )
            } else {
                return@setOnClickListener
            }
        }

    }

    override fun getViewModel(): BaseViewModel = viewModel
}
