package com.tailorfit.android.tailorfitapp.repositories

import android.util.Log
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel
import timber.log.Timber
import javax.inject.Inject

class GigsRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    private val gigImageModelA = GigImageModel("1st", "a")
    private val gigImageModelB = GigImageModel("2nd", "b")
    private val gigImageModelC = GigImageModel("3rd", "c")
    private val gigImageModelD = GigImageModel("4th", "d")

    private val imagePlaceHolderList =
        listOf(gigImageModelA, gigImageModelB, gigImageModelC, gigImageModelD)

    fun getImagePlaceHolder(): List<GigImageModel> = imagePlaceHolderList


}