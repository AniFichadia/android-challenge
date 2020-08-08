package com.anifichadia.employeehub.service.retrofit

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface Mapper<FromT, ToT> {
    @Throws(Throwable::class)
    fun map(from: FromT): ToT
}
