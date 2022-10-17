package org.sopt.sample.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.anchorSnackBar
import org.sopt.sample.util.snackBar

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() { // setOnClickListener 이벤트 함수
        binding.tvSignupDonebtn.setOnClickListener {
            checkText()
        }
    }

    private fun checkText() { // 회원가입 텍스트 분기처리, snackBar
        with(binding) {
            val etId = etSignupId.text.toString()
            val etPw = etSignupPw.text.toString()

            if (etId.isEmpty() or etPw.isEmpty()) {
                root.snackBar(R.string.sb_signup_necessary)
            } else if (etId.length < 6) {
                tvSignupId.anchorSnackBar(R.string.sb_signup_id)
            } else if (etPw.length < 8) {
                tvSignupPw.anchorSnackBar(R.string.sb_signup_pw)
            } else {
                root.snackBar(R.string.sb_signup_done)
                passIntent(etId, etPw)
            }
        }
    }

    private fun passIntent(etId: String, etPw: String) { // SignInActivity 텍스트 데이터 전달
        val intentToSignIn = Intent(this, SignInActivity::class.java).apply {
            putExtra("id", etId)
            putExtra("pw", etPw)
        }
        setResult(Activity.RESULT_OK, intentToSignIn)
        finish()
    }
}
