package com.vivekvishwanath.intentssh

import android.net.Uri
import java.io.Serializable

class ImageData(uri: Uri? = null, var name: String = ""): Serializable {

    companion object {
        const val IMAGE_TAG = "image"
    }

    private var uriString = uri.toString()

    fun getUri(): Uri {
        return Uri.parse(uriString)
    }

    fun setUri(uri: Uri?) {
        uriString = uri.toString()
    }
}