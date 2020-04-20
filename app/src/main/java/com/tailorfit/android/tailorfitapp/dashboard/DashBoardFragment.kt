package com.tailorfit.android.tailorfitapp.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentDashBoardBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import javax.inject.Inject


class DashBoardFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var binding: FragmentDashBoardBinding

//    private lateinit var dashBoardViewPagerAdapter: DashBoardViewPagerAdapter

    val tabLayoutList = listOf("Pending Jobs", "Completed Jobs")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashBoardBinding.inflate(inflater)
        return binding.root
    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        daggerAppComponent.inject(this)
////        binding.tabLayoutViewHolder.adapter = DashBoardViewPagerAdapter(this)
////        TabLayoutMediator(binding.tabs, binding.tabLayoutViewHolder,
////            TabLayoutMediator.OnConfigureTabCallback { tab, position ->
////                when(position) {
////                    0 -> tab.text = tabLayoutList[0]
////                    1 -> tab.text = tabLayoutList[1]
////                }
////            }).attach()
//    }
//
//    override fun getViewModel(): BaseViewModel {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
////    private inner class DashBoardViewPagerAdapter(fragment: Fragment) :
////        FragmentStateAdapter(fragment) {
////        private lateinit var fragment: Fragment
////
////        override fun getItemCount(): Int = tabLayoutList.size
////
////        override fun createFragment(position: Int): Fragment {
////            when (position) {
////
////                0 -> {
////                    fragment = PendingJobsFragment()
////                }
////
////                1 -> {
////                    fragment = CompletedJobsFragment()
////                }
////
////            }
////            return fragment
////        }
////    }
}