package org.sopt.sample.data.remote.request

import kotlinx.serialization.Serializable

@Serializable
data class RequestSignUp(
    val email: String,
    val password: String,
    val name: String
)
