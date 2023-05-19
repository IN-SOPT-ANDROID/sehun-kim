package org.sopt.sample.presentation.login.signup

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.sample.data.local.SignUpMessage
import org.sopt.sample.data.remote.CreateService
import org.sopt.sample.data.remote.api.SignUpService
import org.sopt.sample.data.remote.request.RequestSignUp
import org.sopt.sample.util.addSourceList

class SignUpViewModel : ViewModel() {
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    private val signUpService: SignUpService = CreateService.signUpService

    val checkInput = MediatorLiveData<Int>().apply {
        addSourceList(email, password, name) {
            checkSignUpInput()
        }
    }

    fun connectNetwork() {
        viewModelScope.launch {
            runCatching {
                signUpService.postSignUp(
                    RequestSignUp(
                        email.value!!,
                        password.value!!,
                        name.value!!
                    )
                )
            }.onSuccess {
                Log.d("*****success*****", "error:$it")
            }.onFailure {
                Log.d("*****NETWORK*****", "error:$it")
            }
        }
    }

    private fun checkSignUpInput(): Int {
        return if (email.value.isNullOrBlank() or
            password.value.isNullOrBlank() or
            name.value.isNullOrBlank()
        ) {
            SignUpMessage.EMPTY_INPUT.message
        } else if (!email.value!!.contains('@')) {
            SignUpMessage.NOT_EMAIL.message
        } else if (password.value!!.length < 8) {
            SignUpMessage.PASSWORD_RULE.message
        } else SignUpMessage.SIGNUP_SUCCESS.message
    }
}
