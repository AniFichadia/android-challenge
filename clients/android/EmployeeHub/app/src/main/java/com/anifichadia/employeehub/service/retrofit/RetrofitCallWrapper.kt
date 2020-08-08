package com.anifichadia.employeehub.service.retrofit

import com.anifichadia.employeehub.service.ApiResult
import retrofit2.Call

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class RetrofitCallWrapper<NetworkResponseT, MappedT>(
    private val mapper: Mapper<NetworkResponseT, MappedT>
) {
    fun execute(call: Call<NetworkResponseT>): ApiResult<MappedT> {
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                ApiResult.Success(
                    response.code(),
                    mapper.map(response.body()!!)
                )
            } else {
                ApiResult.FailureWithResponse(response.code())
            }
        } catch (e: Throwable) {
            ApiResult.FailureWithException(e)
        }
    }
}
