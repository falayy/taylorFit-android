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

import com.tailorfit.android.Constants.ImageUpload
import com.tailorfit.android.extensions.resize
import okhttp3.RequestBody
import okhttp3.internal.closeQuietly
import okio.BufferedSink
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.Source
import okio.source
import java.io.File
import java.io.IOException

class SizedFileRequestBody @JvmOverloads constructor(
    private val file: File,
    private val height: Int = ImageUpload.DEFAULT_IMAGE_UPLOAD_SIZE.height,
    private val width: Int = ImageUpload.DEFAULT_IMAGE_UPLOAD_SIZE.width
) : RequestBody() {

    private var isFileResized = false

    override fun contentType() = ImageUpload.IMAGE_UPLOAD_MEDIA_TYPE.toMediaTypeOrNull()

    override fun contentLength(): Long {
        if (!isFileResized) {
            file.resize(height, width)
            isFileResized = true
        }
        return file.length()
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        var source: Source? = null
        try {
            source = file.source()
            sink.writeAll(source)
        } finally {
            source?.closeQuietly()
        }
    }
}
