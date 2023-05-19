package org.sopt.sample.presentation.login.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel() : ViewModel() {
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _isChecked = MutableLiveData<Boolean>()
    val isChecked: LiveData<Boolean> get() = _isChecked

    fun log() = Log.d("dd", isChecked.toString())
}
