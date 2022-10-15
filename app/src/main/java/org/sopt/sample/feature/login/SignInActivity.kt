package org.sopt.sample.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.feature.home.MainActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isClickEvent()
    }

    private fun isClickEvent() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)

        with(binding) {
            tvSigninSignupbtn.setOnClickListener {
                startActivity(intentToSignUp)
            }

            tvSigninLoginbtn.setOnClickListener {
                startActivity(intentToMain)
            }
        }
    }
}
