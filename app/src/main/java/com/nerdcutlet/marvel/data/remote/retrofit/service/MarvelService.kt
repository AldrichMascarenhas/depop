package com.nerdcutlet.marvel.data.remote.retrofit.service

import com.nerdcutlet.marvel.data.remote.retrofit.response.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("orderBy") orderBy: String

    ): Response<CharactersResponse>

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): Response<CharactersResponse>
}
