package org.sopt.sample.presentation.login

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import org.sopt.sample.data.local.LoginInfo
import org.sopt.sample.util.LoginSharedPreferences

class SignInViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext
    private val loginSharedPreferences by lazy { LoginSharedPreferences(context) }

    fun setSharedPreferences(id: String, pw: String, value: Boolean) {
        // 유저데이터 및 자동로그인 체크 여부 전달
        with(loginSharedPreferences) {
            saveLoginInfo(
                LoginInfo(
                    id = id,
                    pw = pw
                )
            )
            setAutoLogin(value)
        }
    }

    fun checkAutoLogin(): Boolean {
        // SignInActivity True/False 값 넘겨줌
        return loginSharedPreferences.getAutoLogin(context)
    }
}
