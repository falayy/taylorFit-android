package com.tailorfit.android.tailorfitapp.userdashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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

    private lateinit var dashBoardViewModel: DashBoardViewModel

    private lateinit var binding: FragmentDashBoardBinding


    val tabLayoutList = listOf("Pending Jobs", "Completed Jobs")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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

        dashBoardViewModel.userInfo(prefsValueHelper.getAccessToken())

        dashBoardViewModel.userInfoResponse.observe(viewLifecycleOwner, Observer {
            binding.userNameTextView.text = it.username
            binding.userPhoneTextView.text = it.phoneNumber
        })

    }

    override fun getViewModel(): BaseViewModel = dashBoardViewModel


    private inner class DashBoardViewPagerAdapter(fragment: Fragment) :
        FragmentStateAdapter(fragment) {
        private lateinit var fragment: Fragment

        override fun getItemCount(): Int = tabLayoutList.size

        override fun createFragment(position: Int): Fragment {
            when (position) {

                0 -> {
                    fragment = PendingJobsFragment()
                }

                1 -> {
                    fragment = CompletedJobsFragment()
                }
            }
            return fragment
        }
    }
}