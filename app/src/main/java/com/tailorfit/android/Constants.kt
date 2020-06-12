package com.tailorfit.android

import com.tailorfit.android.tailorfitapp.models.local.ImageSize

object Constants {

    object ImageUpload {
        const val IMAGE_UPLOAD_MEDIA_TYPE = "application/octet-stream"
        val DEFAULT_IMAGE_UPLOAD_SIZE = ImageSize(400, 600)
    }

    object Misc {
        const val FILE_PROVIDER_AUTHORITY = "${BuildConfig.APPLICATION_ID}.fileprovider"
    }

    object  Gender{
        const val FEMALE = "female"
        const val MALE = "male"
    }

}
