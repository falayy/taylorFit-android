package com.tailorfit.android.tailorfitapp.test


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tailorfit.android.base.BaseFragment

import com.tailorfit.android.databinding.FragmentTestyBinding

/**
 * A simple [Fragment] subclass
 * for testing incase of bugs or whatever comes
 */

class TestyFragment : BaseFragment() {


    private lateinit var binding : FragmentTestyBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView.loadUrl("https://github.com/JetBrains/kotlin")
    }


}
