package com.nerdcutlet.marvel.data.remote.retrofit.service

import com.nerdcutlet.marvel.data.remote.retrofit.response.DepopItemResponse
import com.nerdcutlet.marvel.data.remote.retrofit.response.DepopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("products/popular/")
    suspend fun getCharacters(): Response<DepopResponse>


    @GET("products/{characterId}/")
    suspend fun getCharacterById(
        @Path("characterId") characterId: String
    ): Response<DepopItemResponse>



}
