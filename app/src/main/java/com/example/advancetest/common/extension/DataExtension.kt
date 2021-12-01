package com.example.advancetest.common.extension

import com.example.advancetest.common.const.GSON_TO_CAST
import com.example.advancetest.common.error.ErrorDomain
import com.example.advancetest.common.error.ErrorEntity
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

fun ResponseBody.toErrorEntity(): ErrorDomain {
    return GSON_TO_CAST.fromJson(
        this.charStream(),
        ErrorEntity::class.java
    )
}