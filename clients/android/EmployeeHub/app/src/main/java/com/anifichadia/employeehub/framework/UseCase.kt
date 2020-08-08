package com.anifichadia.employeehub.framework

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface UseCase<InputT, OutputT> {
    suspend fun execute(input: InputT): OutputT
}
