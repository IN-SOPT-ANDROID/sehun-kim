package org.sopt.sample.presentation.login.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.data.local.SignUpMessage
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.presentation.login.signin.SignInActivity
import org.sopt.sample.util.anchorSnackBar
import org.sopt.sample.util.snackBar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        setClickListeners()
    }

    private fun initViewModel() {
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = this
    }

    private fun observeCheckInput(): Int {
        var stateMessage = 0
        signUpViewModel.checkInput.observe(this) {
            stateMessage = it
        }
        return stateMessage
    }

    private fun setClickListeners() {
        binding.tvSignupDonebtn.setOnClickListener {
            printState()
            if (observeCheckInput() == SignUpMessage.SIGNUP_SUCCESS.message) startActivity()
        }
    }

    private fun printState() {
        val stateMessage = observeCheckInput()
        with(binding) {
            when (stateMessage) {
                SignUpMessage.EMPTY_INPUT.message -> root.snackBar(stateMessage)
                SignUpMessage.NOT_EMAIL.message -> tvSignupId.anchorSnackBar(stateMessage)
                SignUpMessage.PASSWORD_RULE.message -> tvSignupPw.anchorSnackBar(stateMessage)
                SignUpMessage.SIGNUP_SUCCESS.message -> root.snackBar(stateMessage)
            }
        }
    }

    private fun startActivity() {
        setIntent()
        finish()
    }

    private fun setIntent() { // SignInActivity 텍스트 데이터 전달
        val intentToSignIn = Intent(this, SignInActivity::class.java).apply {
            putExtra("id", binding.etSignupId.text)
            putExtra("pw", binding.etSignupPw.text)
        }
        setResult(Activity.RESULT_OK, intentToSignIn)
    }
}
