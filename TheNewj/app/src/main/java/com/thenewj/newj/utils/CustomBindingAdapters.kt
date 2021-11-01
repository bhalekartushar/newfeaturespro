package com.thenewj.newj.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.thenewj.newj.R


@BindingAdapter("imageCircle")
fun loadCircleImage(view: AppCompatImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .placeholder(R.drawable.shape_circle)
        .into(view)
}

@BindingAdapter("image")
fun loadImage(view: AppCompatImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .placeholder(R.drawable.img_default_news_card)
        .error(R.drawable.img_default_news_card)
        .into(view)
}
