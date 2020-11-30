package com.nerdcutlet.depop.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName

data class DepopItemResponse(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("pictures_data")
    val picturesData: List<PicturesData>
)
