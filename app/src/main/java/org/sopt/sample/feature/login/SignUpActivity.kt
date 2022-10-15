package org.sopt.sample.feature.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignUpBinding
import org.sopt.sample.util.shortToast

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickEvent()
    }

    private fun clickEvent() {
        binding.tvSignupDonebtn.setOnClickListener {
            checkText()
        }
    }

    private fun checkText() {
        with(binding) {
            val etId = etSignupId.text.toString()
            val etPw = etSignupPw.text.toString()

            if (etId.isEmpty() or etPw.isEmpty()) {
                shortToast("ID와 PW는 필수항목입니다")
            } else if (etId.length < 6) {
                shortToast("최소 6글자가 필요합니다")
            } else if (etPw.length < 8) {
                shortToast("최소 8글자가 필요합니다")
            } else {
                passIntent(etId, etPw)
            }
        }
    }

    private fun passIntent(etId: String, etPw: String) {
        val intentToSignIn = Intent(this, SignInActivity::class.java).apply {
            putExtra("id", etId)
            putExtra("pw", etPw)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}
