package org.sopt.sample.data.remote.api

import org.sopt.sample.data.remote.request.RequestSignUp
import org.sopt.sample.data.remote.response.ResponseSignUp
import org.sopt.sample.data.remote.response.ResponseWrapper
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {
    @POST("api/user/signup")
    suspend fun postSignUp(@Body body: RequestSignUp): ResponseWrapper<ResponseSignUp>
}
