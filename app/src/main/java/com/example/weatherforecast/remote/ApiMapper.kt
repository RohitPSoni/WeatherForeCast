package com.example.weatherforecast.remote

import com.google.gson.Gson
import retrofit2.Response
import com.example.weatherforecast.remote.data.Error

interface ApiMapper {
    suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T>
}

class ApiMapperImpl: ApiMapper{
    override suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        return runCatching {
            val response = call.invoke()
            if(response.isSuccessful) {
            response.body()?.let {
                Result.success(it)
            }?:run {
                Result.failure(Exception("response.body() can't be null"))
            }
            } else {
                val errorBody = response.errorBody()
                errorBody?.let {
                    val error = Gson().fromJson(it.string(), Error::class.java)
                    Result.failure(Exception(error.message?:"Unknown Error"))
                } ?: run {
                    Result.failure(Exception("Unknown Error"))
                }
            }
        }.getOrElse {
            return Result.failure(it)
        }
    }

}