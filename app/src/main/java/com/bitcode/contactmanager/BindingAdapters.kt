package com.bitcode.contactmanager

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("my_image_id")
fun setImageIdToImageView(imageView: ImageView, imageId : Int) {
    imageView.setImageResource(imageId)
}