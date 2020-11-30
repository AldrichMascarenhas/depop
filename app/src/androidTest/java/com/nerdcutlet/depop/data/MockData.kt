package com.nerdcutlet.depop.data

import com.nerdcutlet.depop.data.remote.retrofit.response.CharactersResponse
import com.nerdcutlet.depop.data.remote.retrofit.response.Data
import com.nerdcutlet.depop.data.remote.retrofit.response.Result
import com.nerdcutlet.depop.data.remote.retrofit.response.Thumbnail
import com.nerdcutlet.depop.domain.model.ProductDomainModel

object MockDataAndroidTest{

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

    fun getHeroDomainModel () = ProductDomainModel(
        id = 1,
        name = "",
        description = "",
        heroThumbnailExtension = "",
        heroThumbnailPath = ""
    )

    fun getListHeroDomainModel() = listOf(getHeroDomainModel())

}