package com.thenewj.newj.ext

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import kotlin.math.floor
import kotlin.math.roundToInt


fun String?.layoutWidthParam(context: Context): Int {
    return when {
        this == "wrap_content" -> LinearLayout.LayoutParams.WRAP_CONTENT
        this == "match_parent" -> LinearLayout.LayoutParams.MATCH_PARENT
        this?.contains("dp") == true -> (this.substring(
            0,
            this.indexOf("dp")
        ).toInt().toPixel(context))
        else -> LinearLayout.LayoutParams.MATCH_PARENT
    }
}

fun Int.toPixel(c: Context): Int {
    val scale: Float = c.resources.displayMetrics.density
    return floor(this * scale + 0.5f).roundToInt()
}


fun String?.toMargin(c: Context): Int {
    val index: Int? = this?.indexOf("dp")
    return this?.substring(
        0, index ?: this.length
    )?.toInt()?.toPixel(c) ?: 0
}

fun String?.toScaleType(): ImageView.ScaleType? {
    return when {
        this.equals("center") -> ImageView.ScaleType.CENTER
        this.equals("center_crop") -> ImageView.ScaleType.CENTER_CROP
        this.equals("center_inside") -> ImageView.ScaleType.CENTER_INSIDE
        this.equals("fit_center") -> ImageView.ScaleType.FIT_CENTER
        this.equals("fit_end") -> ImageView.ScaleType.FIT_END
        else -> ImageView.ScaleType.CENTER
    }
}

fun String?.toGravity(): Int {
    return when {
        this.equals("center") -> Gravity.CENTER
        this.equals("end") -> Gravity.END
        this.equals("start") -> Gravity.START
        this.equals("top") -> Gravity.TOP
        this.equals("bottom") -> Gravity.BOTTOM
        else -> Gravity.START
    }
}