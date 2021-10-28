package com.thenewj.newj.data.remote.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Child {
    @SerializedName("layout_width")
    @Expose
    var layoutWidth: String? = null

    @SerializedName("gravity")
    @Expose
    var gravity: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("layout_marginTop")
    @Expose
    var layoutMarginTop: String? = null

    @SerializedName("max")
    @Expose
    var max: Int? = null

    @SerializedName("progress")
    @Expose
    var progress: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("layout_marginBottom")
    @Expose
    var layoutMarginBottom: String? = null

    @SerializedName("backgroundColor")
    @Expose
    var backgroundColor: String? = null

    @SerializedName("textColor")
    @Expose
    var textColor: String? = null
}