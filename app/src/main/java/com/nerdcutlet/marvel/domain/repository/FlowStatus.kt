package com.nerdcutlet.marvel.domain.repository

import androidx.annotation.Nullable
import com.nerdcutlet.marvel.data.remote.okhttp.NetworkException
import com.nerdcutlet.marvel.domain.Status
import kotlinx.coroutines.flow.flow
import retrofit2.Response

abstract class FlowStatus<RequestType : Any, ResultType : Any> {

    val asFlow = flow<Status<ResultType>> {

        emit(Status.Loading)

        try {
            networkCall()?.let { response ->
                if (!response.isSuccessful) {
                    // TODO : Wrap exception in custom handler
                    emit(Status.Error(Exception(response.raw().toString())))
                } else {
                    val data = mapData(requestType = response.body()!!)
                    emit(Status.Success(data))
                }
            }
        } catch (exception: NetworkException) {
            emit(Status.Error(Exception()))
        }

        databaseCall()?.let {
            val data = mapData(it)
            emit(Status.Success(data))
        }
    }

    @Nullable
    protected abstract suspend fun networkCall(): Response<RequestType>?

    @Nullable
    protected abstract suspend fun databaseCall(): RequestType?

    protected abstract fun mapData(requestType: RequestType): ResultType
}
