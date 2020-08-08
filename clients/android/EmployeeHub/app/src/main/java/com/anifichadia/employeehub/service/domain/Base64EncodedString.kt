package com.anifichadia.employeehub.service.domain

import android.util.Base64


/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class Base64EncodedString(
    val rawString: String
) {

    val decodedBytes: ByteArray = Base64.decode(rawString, Base64.DEFAULT)
    val decodedBytesLength: Int = decodedBytes.size

}
