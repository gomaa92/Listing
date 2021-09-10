package com.nagwa.listing.data.model

import android.graphics.Bitmap

data class GetListOfFilesResponse(
    val id: Int,
    val type: String,
    val url: String,
    val name: String,
)