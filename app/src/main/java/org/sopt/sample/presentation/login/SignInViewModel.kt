package org.sopt.sample.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignInViewModel() : ViewModel() {
    private val _isChecked = MutableLiveData<Boolean>()
    val isChecked: LiveData<Boolean> get() = _isChecked

    fun log() = Log.d("dd", isChecked.toString())
}
