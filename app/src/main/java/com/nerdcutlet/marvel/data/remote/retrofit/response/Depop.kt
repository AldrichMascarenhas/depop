package com.nerdcutlet.marvel.data.remote.retrofit.response


import com.google.gson.annotations.SerializedName

data class DepopResponse(
    @SerializedName("objects")
    val objects: List<Object>
)

data class Object(
    @SerializedName("active_status")
    val activeStatus: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("brand_id")
    val brandId: Int,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("country")
    val country: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("hand_delivery")
    val handDelivery: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("international_shipping_cost")
    val internationalShippingCost: String,
    @SerializedName("national_shipping_cost")
    val nationalShippingCost: String,
    @SerializedName("pictures_data")
    val picturesData: List<PicturesData>,
    @SerializedName("price_amount")
    val priceAmount: String,
    @SerializedName("price_currency")
    val priceCurrency: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("purchase_via_paypal")
    val purchaseViaPaypal: Boolean,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("user_id")
    val userId: Int
)


data class PicturesData(
    @SerializedName("formats")
    val formats: Formats,
    @SerializedName("id")
    val id: Int
)

data class Formats(
    @SerializedName("P0")
    val p0: P0,
    @SerializedName("P1")
    val p1: P1,
    @SerializedName("P2")
    val p2: P2,
    @SerializedName("P4")
    val p4: P4,
    @SerializedName("P5")
    val p5: P5,
    @SerializedName("P6")
    val p6: P6,
    @SerializedName("P7")
    val p7: P7,
    @SerializedName("P8")
    val p8: P8
)
data class P0(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P1(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P2(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P3(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P4(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P5(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P6(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P7(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)

data class P8(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int
)