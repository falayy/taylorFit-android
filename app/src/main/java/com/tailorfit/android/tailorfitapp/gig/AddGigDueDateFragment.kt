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

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseGigFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.GigFormType

class AddGigDueDateFragment : BaseGigFormFragment() {

    override fun getGigFormType(): GigFormType = GigFormType.AddGigDueDateFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.gig_due_date_message)
        binding.formValueInputLayout.hint = getString(R.string.due_date)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.editText.focusable = View.NOT_FOCUSABLE
        }
    }
}