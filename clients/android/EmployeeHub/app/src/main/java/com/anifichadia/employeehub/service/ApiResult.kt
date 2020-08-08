package com.anifichadia.employeehub.service

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
sealed class ApiResult<DataT> {
    class Success<DataT>(
        val httpStatusCode: Int,
        val data: DataT
    ) : ApiResult<DataT>()

    class FailureWithResponse<DataT>(
        val httpStatusCode: Int
    ) : ApiResult<DataT>()

    class FailureWithException<DataT>(
        val throwable: Throwable
    ) : ApiResult<DataT>()
}
