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
package com.tailorfit.android.tailorfitapp.gig

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.networkutils.Result
import com.tailorfit.android.networkutils.LoadingStatus
import com.tailorfit.android.networkutils.disposeBy
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel
import com.tailorfit.android.tailorfitapp.models.response.CreateGigResponse
import com.tailorfit.android.tailorfitapp.repositories.GigsRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

enum class ImageUploadStatus { NOT_UPLOADED, UPLOADING, SUCCESS, FAILED }

class GigViewModel @Inject constructor(
    private val gigsRepository: GigsRepository,
    private val prefsValueHelper: PrefsValueHelper
) :
    BaseViewModel() {

    private val _imagePlaceHolder = MutableLiveData<List<GigImageModel>>()

    val imagePlaceHolder: LiveData<List<GigImageModel>>
        get() = _imagePlaceHolder

    private val _gigImageUploadStatus =
        MutableLiveData<ImageUploadStatus>(ImageUploadStatus.NOT_UPLOADED)

    val imageUploadStatus: LiveData<ImageUploadStatus>
        get() = _gigImageUploadStatus

    private val _uriResponse = MutableLiveData<Uri>()

    private val _createGigResponse = MutableLiveData<CreateGigResponse>()
    val createGigResponse: LiveData<CreateGigResponse> = _createGigResponse

    fun getImagePlaceHolders() {
        _imagePlaceHolder.value = gigsRepository.getImagePlaceHolder()
    }

    fun uploadGigStyle(
        photoUri: Uri
    ) {
        _gigImageUploadStatus.value = ImageUploadStatus.UPLOADING
        gigsRepository.uploadImage(photoUri)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        _uriResponse.value = it.data
                        _gigImageUploadStatus.value = ImageUploadStatus.SUCCESS
                    }
                    is Result.Error -> {
                        _gigImageUploadStatus.value = ImageUploadStatus.FAILED
                    }
                }
            }.disposeBy(disposeBag)
    }

    fun createGig(token: String, createGigRequest: CreateGigRequest) {
        _loadingStatus.value = LoadingStatus.Loading("Creating Gig, please wait...")
        gigsRepository.createGig(token, createGigRequest)
            .subscribeBy {
                when (it) {
                    is Result.Success -> {
                        prefsValueHelper.setGigId(it.data.id)
                        _createGigResponse.value = it.data
                        _loadingStatus.value = LoadingStatus.Success
                    }
                    is Result.Error -> _loadingStatus.value =
                        LoadingStatus.Error(it.errorCode, it.errorMessage)
                }
            }.disposeBy(disposeBag)
    }

    override fun addAllLiveDataToObservablesList() {
        addAllLiveDataToObservablesList(
            createGigResponse
        )
    }
}
