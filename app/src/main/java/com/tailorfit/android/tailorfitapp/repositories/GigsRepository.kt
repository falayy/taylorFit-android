/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.repositories

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.tailorfit.android.Constants
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toSingle
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
import com.tailorfit.android.tailorfitapp.models.local.KeyValue
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel
import com.tailorfit.android.tailorfitapp.models.response.CreateGigResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import javax.inject.Inject

class GigsRepository @Inject constructor(private val tailorFitApIService: TailorFitApIService) {

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    private val gigImageModelA = GigImageModel("1st", "a")
    private val gigImageModelB = GigImageModel("2nd", "b")
    private val gigImageModelC = GigImageModel("3rd", "c")
    private val gigImageModelD = GigImageModel("4th", "d")

    private val maleStyleLists = mutableListOf(
        KeyValue("Shirt", "Shirt", "1"),
        KeyValue("Pants", "Pants", "2"),
        KeyValue("Shorts", "Shorts", "3"),
        KeyValue("Kaftan", "Kaftan", "4"),
        KeyValue("Shorts", "Shorts", "5"),
        KeyValue("Native Attire", "Native Attire", "6")
    )


    private val femaleStyleLists = mutableListOf(
        KeyValue("Jackets", "Jackets", "1"),
        KeyValue("Top & Pants", "Top & Pants", "2"),
        KeyValue("Top & Skirts", "Top & Skirts", "3"),
        KeyValue("Kimono & Pants", "Kimono & Pants", "4"),
        KeyValue("Jackets & Skirts", "Jackets & Skirts", "5"),
        KeyValue("Dresses", "Dresses", "6"),
        KeyValue("Trousers", "Trousers", "7"),
        KeyValue("Skirts", "Skirts", "8"),
        KeyValue("Tops", "Tops", "9"),
        KeyValue("Traditional", "Traditional", "10")
    )

    private val imageStyle = mutableListOf<String>()

    private val imagePlaceHolderList =
        listOf(gigImageModelA, gigImageModelB, gigImageModelC, gigImageModelD)

    fun getImagePlaceHolder(): List<GigImageModel> = imagePlaceHolderList

    fun getStyles(gender : String) : List<KeyValue> {
        return if (gender == Constants.Gender.MALE) {
            maleStyleLists
        } else {
            femaleStyleLists
        }
    }

    // ------uploads style images to firebase

    fun uploadImage(imageFilePath: Uri?): Single<Result<Uri>> {
        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        val photoRef = storageReference?.child("imageStyleUploads/${UUID.randomUUID()}")
        return photoRef?.putFile(imageFilePath!!)?.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }
            photoRef.downloadUrl
        }?.addOnCompleteListener {
            if (it.isSuccessful) {
                imageStyle.add(it.result.toString())
            }
        }?.toSingle()!!.map {
            Result.Success(it) as Result<Uri>
        }.observeOn(AndroidSchedulers.mainThread())
    }

    fun createGig(token: String, createGigRequest: CreateGigRequest):
            Single<Result<CreateGigResponse>> {
        createGigRequest.style = imageStyle
        return tailorFitApIService.createGig(token, createGigRequest).toResult()
    }
}
