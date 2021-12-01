package com.example.advancetest.common.error

import com.example.advancetest.common.const.EMPTY_STRING

abstract class ErrorDomain

data class ErrorEntity(
    val message: String = EMPTY_STRING,
    val code: String = EMPTY_STRING,
): ErrorDomain()