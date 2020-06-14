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
package com.tailorfit.android.tailorfitapp.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentDemoPagerBinding
import com.tailorfit.android.tailorfitapp.models.local.PageDataModel

class DemoPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentDemoPagerBinding

    companion object {
        private const val PAGER_MODEL_KEY = "pager_model"
        fun newInstance(pagerDataModel: PageDataModel): DemoPagerFragment = DemoPagerFragment().also {
            it.arguments = Bundle().apply { putParcelable(PAGER_MODEL_KEY, pagerDataModel) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDemoPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagerDataModel = arguments!!.get(PAGER_MODEL_KEY) as PageDataModel
        binding.descriptionTextView.text = getString(pagerDataModel.description)
        binding.illustrationImage.setImageResource(pagerDataModel.illustrationGraphic)
        binding.titleTextView.text = getString(pagerDataModel.title)
    }
}
