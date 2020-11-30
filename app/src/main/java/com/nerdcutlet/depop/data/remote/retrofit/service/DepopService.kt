package com.nerdcutlet.depop.data.remote.retrofit.service

import com.nerdcutlet.depop.data.remote.retrofit.response.DepopItemResponse
import com.nerdcutlet.depop.data.remote.retrofit.response.DepopResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DepopService {

    @GET("products/popular/")
    suspend fun getProducts(): Response<DepopResponse>

    @GET("products/{id}/")
    suspend fun getProductsById(
        @Path("id") id: String
    ): Response<DepopItemResponse>
}
