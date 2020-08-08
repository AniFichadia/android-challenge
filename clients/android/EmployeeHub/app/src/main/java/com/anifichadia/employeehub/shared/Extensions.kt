package com.anifichadia.employeehub.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.anifichadia.employeehub.service.domain.Base64EncodedString

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */

fun Base64EncodedString.toBitmap(): Bitmap? {
    return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytesLength)
}
