package com.nerdcutlet.depop.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName

data class DepopResponse(
    @SerializedName("objects")
    val products: List<Product>
)

data class Product(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pictures_data")
    val picturesData: List<PicturesData>
)

data class PicturesData(
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("id")
    val id: Int
)

data class Formats(
    @SerializedName("P0")
    val p0: P0
)
data class P0(
    @SerializedName("url")
    val url: String
)
