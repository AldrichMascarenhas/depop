package com.nerdcutlet.marvel.data.remote.retrofit.response

import com.google.gson.annotations.SerializedName
import com.nerdcutlet.marvel.data.local.entities.HeroDatabaseModel
import com.nerdcutlet.marvel.domain.model.HeroDomainModel

data class CharactersResponse(
    @SerializedName("attributionHTML")
    val attributionHTML: String,
    @SerializedName("attributionText")
    val attributionText: String,
    @SerializedName("code")
    val code: Int,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: Data,
    @SerializedName("etag")
    val etag: String,
    @SerializedName("status")
    val status: String
)

data class Data(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total")
    val total: Int
)

data class Result(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
)

fun Result.toHeroDatabaseModel(): HeroDatabaseModel {

    return HeroDatabaseModel(
        id = id,
        name = name,
        heroThumbnailPath = thumbnail.path,
        heroThumbnailExtension = thumbnail.extension
    )
}

fun Result.toHeroDomainModel(): HeroDomainModel {

    return HeroDomainModel(
        id = id,
        name = name,
        description = description,
        heroThumbnailPath = thumbnail.path,
        heroThumbnailExtension = thumbnail.extension
    )
}

data class Thumbnail(
    @SerializedName("extension")
    val extension: String,
    @SerializedName("path")
    val path: String
)
