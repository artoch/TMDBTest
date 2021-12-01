package com.example.advancetest.common.state

import com.example.advancetest.common.error.ErrorDomain

data class DataState<T>(
    val error: ErrorDomain? = null,
    val data: T? = null,
    val isLoading: Boolean = false,
) {

    companion object {

        fun <T> errorData(error: ErrorDomain): DataState<T> = DataState(error = error)

        fun <T> data(data: T? = null): DataState<T> = DataState(data = data)

        fun <T> loading() = DataState<T>(isLoading = true)

    }
}