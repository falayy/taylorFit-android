package com.tailorfit.android.tailorfitapp.customerdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment


class CustomerDetailsFragment : BaseViewModelFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_details, container, false)
    }

    override fun getViewModel(): BaseViewModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
