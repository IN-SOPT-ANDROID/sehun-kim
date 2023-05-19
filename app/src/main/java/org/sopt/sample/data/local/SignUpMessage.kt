package org.sopt.sample.data.local

import org.sopt.sample.R

enum class SignUpMessage(val message: Int) {
    EMPTY_INPUT(R.string.sb_signup_necessary),
    NOT_EMAIL(R.string.sb_signup_email),
    PASSWORD_RULE(R.string.sb_signup_pw),
    SIGNUP_SUCCESS(R.string.sb_signup_done)
}
