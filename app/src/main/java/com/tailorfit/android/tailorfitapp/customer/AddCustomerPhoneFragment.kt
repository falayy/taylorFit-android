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

import android.text.InputType
import androidx.lifecycle.ViewModelProviders
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType

class AddCustomerPhoneFragment : BaseCustomerFormFragment() {

    private lateinit var customerViewModel: AddCustomerViewModel

    override fun setUpDaggerViewModel(): AddCustomerViewModel {
        daggerAppComponent.inject(this)
        customerViewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AddCustomerViewModel::class.java)
        return customerViewModel
    }

    override fun getViewModel(): BaseViewModel = customerViewModel

    override fun getCustomerFormType() = CustomerFormType.AddCustomerPhoneFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_number_message)
        binding.formValueInputLayout.hint = getString(R.string.phone_number)
        binding.editText.inputType = InputType.TYPE_CLASS_NUMBER
    }
}
