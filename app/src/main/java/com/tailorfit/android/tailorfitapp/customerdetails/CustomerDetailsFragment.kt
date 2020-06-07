package com.tailorfit.android.tailorfitapp.customerdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.tailorfit.android.Constants
import com.tailorfit.android.Constants.Gender
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentCustomerDetailsBinding
import com.tailorfit.android.extensions.onPageChanged
import com.tailorfit.android.extensions.show
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.intro.DemoPagerFragment
import com.tailorfit.android.tailorfitapp.models.request.AddGigToDoneRequest
import javax.inject.Inject


class CustomerDetailsFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentCustomerDetailsBinding

    private lateinit var customerDetailsViewModel: CustomerDetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    var imageIndex = 0

    private val args by lazy {
        CustomerDetailsFragmentArgs.fromBundle(arguments!!)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomerDetailsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        customerDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CustomerDetailsViewModel::class.java)
        binding.imagesViewpager.adapter = StyleImagePagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.imagesViewpager) { _, _ -> }.attach()
        binding.imagesViewpager.onPageChanged { imageIndex = it }
        val customerDetailsResponseArgs = args.customerDetailsResponse

        if (!customerDetailsResponseArgs.isDone!!) binding.isDoneButton.show()


        binding.apply {
            customerName.text = customerDetailsResponseArgs.customerName
            customerNumberTextView.text = customerDetailsResponseArgs.customerNumber
            styleConstantTextView.text = customerDetailsResponseArgs.styleName
            gigTitleTextView.text = customerDetailsResponseArgs.gigTitle
            gigDueDateTextView.text = customerDetailsResponseArgs.deliveryDate
            gigPriceTextView.text = customerDetailsResponseArgs.price.toString()
        }


        binding.apply {
            viewMeasurementTextView.setOnClickListener {
                findNavController().navigate(
                    CustomerDetailsFragmentDirections.actionCustomerDetailsFragmentToGetMeasurementFragment(customerDetailsResponseArgs)
                )
            }

            isDoneButton.setOnClickListener {
                customerDetailsViewModel.addGigToDone(
                    prefsValueHelper.getAccessToken(),
                    AddGigToDoneRequest(
                        customerDetailsResponseArgs.customerId,
                        customerDetailsResponseArgs.gigId
                    )
                )
            }
        }


    }


    override fun getViewModel(): BaseViewModel = customerDetailsViewModel

    private inner class StyleImagePagerAdapter(fragment: Fragment) :
        FragmentStateAdapter(fragment) {

        override fun getItemCount() = args.customerDetailsResponse.style!!.size

        override fun createFragment(position: Int): Fragment = StyleImagesPagerFragment.newInstance(
            args.customerDetailsResponse.style?.get(imageIndex)
        )

    }

}
