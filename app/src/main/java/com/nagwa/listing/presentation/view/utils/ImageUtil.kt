package com.nagwa.listing.presentation.view.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso

class ImageUtil {
    companion object {
        @Throws(Exception::class)
        fun loadImageFromURL(
            imageView: ImageView?, url: String?,
        ) {
            if (url != null && url != "") {
                Picasso.get()
                    .load(url)
                    .into(imageView)
            } else {
                throw Exception("url is empty")
            }
        }
    }
}