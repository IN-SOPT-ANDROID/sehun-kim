package org.sopt.sample.data.remote

import org.sopt.sample.data.remote.api.SignUpService

object CreateService {
    val signUpService = SoptClient.create<SignUpService>()
}
