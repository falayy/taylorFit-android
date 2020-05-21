package com.tailorfit.android.tailorfitapp.repositories

import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.tailorfit.android.networkutils.toResult
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.toSingle
import com.tailorfit.android.tailorfitapp.apis.TailorFitApIService
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

    private val imageStyle = mutableListOf<String>()

    private val imagePlaceHolderList =
        listOf(gigImageModelA, gigImageModelB, gigImageModelC, gigImageModelD)

    fun getImagePlaceHolder(): List<GigImageModel> = imagePlaceHolderList

    //------uploads style images to firebase

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
            imageStyle.add(task.result.toString())
            photoRef.downloadUrl
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
