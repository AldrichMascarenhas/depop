package com.nerdcutlet.marvel.data

import com.nerdcutlet.marvel.data.remote.retrofit.response.CharactersResponse
import com.nerdcutlet.marvel.data.remote.retrofit.response.Data
import com.nerdcutlet.marvel.data.remote.retrofit.response.Result
import com.nerdcutlet.marvel.data.remote.retrofit.response.Thumbnail
import com.nerdcutlet.marvel.domain.model.HeroDomainModel

object MockData{

    fun getCharacterResponse() : CharactersResponse {
        return CharactersResponse(
            data = Data(
                count = 0,
                limit = 0,
                offset = 0,
                total = 0,
                results = listOf(
                    Result(
                        id = 1,
                        description = "",
                        name = "",
                        thumbnail = Thumbnail(
                            "", ""
                        )

                    )
                )
            ),
            etag = "",
            attributionHTML = "",
            attributionText = "",
            code = 10,
            copyright = "",
            status = ""
        )
    }

    fun getHeroDomainModel () = HeroDomainModel(
        id = 1,
        name = "",
        description = "",
        heroThumbnailExtension = "",
        heroThumbnailPath = ""
    )

    fun getListHeroDomainModel() = listOf(getHeroDomainModel())

}