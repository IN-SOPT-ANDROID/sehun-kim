package org.sopt.sample.data.remote.response

data class ResponseWrapper<T>(
    val status: Int,
    val message: String,
    val newUser: T? = null
)
