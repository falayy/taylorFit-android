package com.tailorfit.android.tailorfitapp.gig


import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseGigFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType
import com.tailorfit.android.tailorfitapp.baseforms.GigFormType
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest


class AddGigStyleFragment : BaseGigFormFragment() {
    private lateinit var createGigRequest: CreateGigRequest
    override fun getGigRequest(): CreateGigRequest = createGigRequest

    override fun getGigFormType(): GigFormType = GigFormType.AddGigStyleFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.gig_style_message)
        binding.editText.hint = getString(R.string.gig_style_hint)
    }


}