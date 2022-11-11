package org.sopt.sample.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseWrapper<T>(
    val status: Int,
    val message: String,
    val newUser: T? = null
)
