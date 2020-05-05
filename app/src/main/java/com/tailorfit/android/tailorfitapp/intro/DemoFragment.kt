package com.tailorfit.android.tailorfitapp.intro


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableField
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentDemoBinding
import com.tailorfit.android.extensions.onPageChanged
import com.tailorfit.android.tailorfitapp.models.local.PageDataModel
import kotlinx.android.synthetic.main.fragment_demo.*

class DemoFragment : BaseFragment() {

    private val slideActionTextValue = ObservableField<String>()

    private lateinit var binding: FragmentDemoBinding

    private var currentPageNumber = 0

    private val dataModels = listOf(
        PageDataModel(R.string.create_shop, R.string.create_shop_description, R.drawable.common_full_open_on_phone),
        PageDataModel(R.string.take_measurement, R.string.take_measurement_description, R.drawable.common_full_open_on_phone),
        PageDataModel(R.string.save_gig, R.string.save_gig_description, R.drawable.common_full_open_on_phone)
    )


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDemoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentPageNumber = 0
        binding.viewPager.adapter = DemoPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
        binding.viewPager.onPageChanged { currentPageNumber = it }
        binding.slideActionButton.setOnClickListener {
            if (currentPageNumber >= dataModels.size - 1) {
               findNavController().navigate(DemoFragmentDirections.actionDemoFragmentToSignUpFragment())
            } else {
                viewPager.currentItem = currentPageNumber + 1
            }
        }

        binding.slideActionButton.setOnClickListener {
            findNavController().navigate(DemoFragmentDirections.actionDemoFragmentToSignUpFragment())
        }
    }

    override fun onBackPressed(): Boolean {
        return if (currentPageNumber == 0) {
            super.onBackPressed()
        } else {
            binding.viewPager.currentItem = currentPageNumber - 1
            true
        }
    }

    private inner class DemoPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount() = dataModels.size

        override fun createFragment(position: Int): Fragment = DemoPagerFragment.newInstance(dataModels[position])
    }

}
