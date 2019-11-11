package dev.rivu.githubrepositories.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import dev.rivu.githubrepositories.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.isVisible() = visibility == View.VISIBLE
fun View.gone() {
    visibility = View.GONE
}

fun ImageView.load(
    url: String,
    requestOptions: RequestOptions = RequestOptions.placeholderOf(R.drawable.circle),
    transformation: BitmapTransformation? = null,
    onLoadingFinished: (isSuccess: Boolean) -> Unit = {}
) {
    val listener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(false)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadingFinished(true)
            return false
        }
    }
    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .listener(listener)
        .apply {
            if(transformation != null) {
                transform(transformation)
            }
        }
        .into(this)
}