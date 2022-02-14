package com.example.apparchitecture.ui.common

import android.util.Log
import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation

class ImageLoaderImpl : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        container.load(url) {
            listener(
                onSuccess = { _, _ ->
                    Log.d("Coil", "Successful loading")
                },
                onError = { _, _ ->
                    Log.d("Coil", "Error loading")
                }
            )

            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

}