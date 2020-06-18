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
package com.tailorfit.android.tailorfitapp.userdashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.tailorfitapp.completedjobs.CompletedJobsFragment
import com.tailorfit.android.databinding.FragmentDashBoardBinding
import com.tailorfit.android.tailorfitapp.pendingjobs.PendingJobsFragment
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import javax.inject.Inject

class DashBoardFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private var storeName = ""

    private lateinit var dashBoardViewModel: DashBoardViewModel

    private lateinit var binding: FragmentDashBoardBinding

    val tabLayoutList = listOf("Pending Jobs", "Completed Jobs")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        dashBoardViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DashBoardViewModel::class.java)

        binding.tabLayoutViewHolder.adapter = DashBoardViewPagerAdapter(this)

        TabLayoutMediator(binding.tabs, binding.tabLayoutViewHolder,
            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                when (position) {
                    0 -> tab.text = tabLayoutList[0]
                    1 -> tab.text = tabLayoutList[1]
                }
            }).attach()

        dashBoardViewModel.apply {

            userInfo(prefsValueHelper.getAccessToken())

            userInfoResponse.observe(viewLifecycleOwner, Observer {
                binding.userNameTextView.text = it.username
                binding.userPhoneTextView.text = it.phoneNumber
                storeName = it.businessName!!
                setUpToolbar()
            })

            cleanUpObservables()
        }

        binding.dashboardFab.setOnClickListener {
            findNavController().navigate(DashBoardFragmentDirections.actionDashBoardFragmentToAddCustomerNameFragment())
        }
    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar(storeName, isDashBoard = true)
        invalidateToolbarElevation(0)
    }

    override fun getViewModel(): BaseViewModel = dashBoardViewModel

    private inner class DashBoardViewPagerAdapter(fragment: Fragment) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int = tabLayoutList.size

        override fun createFragment(position: Int): Fragment {

            return when (position) {

                0 -> {
                    PendingJobsFragment()
                }

                1 -> {
                    CompletedJobsFragment()
                }

                else -> {
                    PendingJobsFragment()
                }
            }
        }
    }
}
