package com.tailorfit.android.tailorfitapp.gig


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tailorfit.android.tailorfit.R
/**
 * A simple [Fragment] subclass.
 */
class AddGigFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_gig, container, false)
    }


}
