package com.example.filmatory.utils

import android.graphics.drawable.Drawable
import android.media.Image
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.filmatory.R
import jp.wasabeef.glide.transformations.BlurTransformation

class BlurImage {
    fun blurImage(activity: AppCompatActivity, int: Int, imageView: ImageView) {
        Glide.with(activity).load(int)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(imageView)
    }
}