package com.nerdcutlet.marvel.data.remote.retrofit.response


import com.google.gson.annotations.SerializedName

data class DepopItemResponse(
    @SerializedName("active_status")
    val activeStatus: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("age")
    val age: List<Any>,
    @SerializedName("brand_id")
    val brandId: Int,
    @SerializedName("categories")
    val categories: List<Int>,
    @SerializedName("colour")
    val colour: List<String>,
    @SerializedName("comment_count")
    val commentCount: Int,
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
    @SerializedName("liked")
    val liked: Boolean,
    @SerializedName("likers_count")
    val likersCount: Int,
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
    @SerializedName("recent_comments")
    val recentComments: List<Any>,
    @SerializedName("saved")
    val saved: Boolean,
    @SerializedName("shipping_methods")
    val shippingMethods: List<Any>,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("source")
    val source: List<Any>,
    @SerializedName("status")
    val status: String,
    @SerializedName("style")
    val style: List<Any>,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("variant_set")
    val variantSet: Int,
    @SerializedName("video_ids")
    val videoIds: List<Any>,
    @SerializedName("videos")
    val videos: List<Any>
)