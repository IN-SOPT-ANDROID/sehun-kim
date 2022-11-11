package org.sopt.sample.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUp(
    val id: Int,
    val name: String,
    val profileImage: String?,
    val bio: String?,
    val email: String,
    val password: String
)
