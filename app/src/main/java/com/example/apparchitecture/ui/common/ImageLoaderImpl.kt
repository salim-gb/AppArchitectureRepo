package com.example.apparchitecture.ui.common

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import coil.imageLoader
import coil.load
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.apparchitecture.ui.common.Extensions.toMD5
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.*

class ImageLoaderImpl : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        val context = container.context

        val imageName = "${url.toMD5()}.jpg"

        val dir = container.context.filesDir

        val imageDir = File(dir, "thumbs")

        if (!File("$dir/thumbs").exists()) {
            imageDir.mkdir()
        }

        val file = imageDir.resolve(imageName)

        if (file.exists()) {
            container.load(File(file.path)) {
                transformations(CircleCropTransformation())
            }
            return
        }

        val request = ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .target(
                onSuccess = { drawable ->

                    container.load(drawable)

                    val bitmap = (drawable as BitmapDrawable).bitmap

                    Flowable.just(bitmap)
                        .onBackpressureBuffer()
                        .subscribeOn(Schedulers.io())
                        .map {
                            createImageFile(it, file)
                        }
                        .subscribe()
                }
            )
            .build()

        container.context.imageLoader.enqueue(request)
    }

    private fun createImageFile(bitmap: Bitmap, file: File) {

        try {
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}