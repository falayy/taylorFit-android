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

    object Gender {
        const val FEMALE = "female"
        const val MALE = "male"
    }
}
