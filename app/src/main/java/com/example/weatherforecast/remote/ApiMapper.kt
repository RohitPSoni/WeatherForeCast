package com.example.weatherforecast.remote

import retrofit2.Response

interface ApiMapper {
    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T>
}

class ApiMapperImpl: ApiMapper{
    override suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        return runCatching {
            val response = call.invoke()
            response.body()?.let {
                Result.success(it)
            }?:run {
                Result.failure(Exception("response.body() can't be null"))
            }

        }.getOrElse {
            return Result.failure(it)
        }
    }

}