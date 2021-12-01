package com.example.advancetest.common.extension

import com.example.advancetest.common.const.BASE_IMAGE_URL

fun String.urlPost() = BASE_IMAGE_URL.plus(this)
