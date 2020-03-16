package com.tailorfit.android.tailorfitapp.gig


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tailorfit.android.databinding.FragmentAddGigDetailsBinding


class AddGigDetails : Fragment() {

    private lateinit var binding : FragmentAddGigDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddGigDetailsBinding.inflate(layoutInflater)
        return binding.root
    }


}
