package com.example.apparchitecture.ui.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.apparchitecture.ui.common.Extensions.toMD5
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

class ImageLoaderImpl : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {

        val dir = container.context.filesDir
        val urlMD5 = url.toMD5()
        val imageName = "$urlMD5.jpg"
        val imagePath = "$dir/$imageName"

        if (File(imagePath).exists()) {
            container.load(File(imagePath)) {
                transformations(CircleCropTransformation())
            }
            return
        }

        val request = ImageRequest.Builder(container.context)
            .data(url)
            .crossfade(true)
            .target(
                onSuccess = { drawable ->
                    container.load(drawable)
                    val bitmap = (drawable as BitmapDrawable).bitmap

                    Flowable.just(bitmap)
                        .onBackpressureBuffer()
                        .subscribeOn(Schedulers.io())
                        .map { img ->
                            createImageFile(container.context, img, imageName)
                        }
                        .subscribe()
                }
            )
            .build()

        container.context.imageLoader.enqueue(request)
    }

    private fun createImageFile(context: Context, bitmap: Bitmap, name: String) {

        try {
            val fos: FileOutputStream =
                context.openFileOutput(name, MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos)
            fos.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}