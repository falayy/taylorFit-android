package com.tailorfit.android.tailorfitapp.gig


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tailorfit.android.R
import com.tailorfit.android.tailorfitapp.forms.BaseFormFragment


class AddGigTitleFragment : BaseFormFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_gig_title, container, false)
    }


}
