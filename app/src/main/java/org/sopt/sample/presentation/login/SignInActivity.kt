package org.sopt.sample.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.data.local.LoginInfo
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.home.MainActivity
import org.sopt.sample.util.LoginSharedPreferences
import org.sopt.sample.util.shortToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var loginSharedPreferences: LoginSharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLauncher()
        clickEvent()
    }

    private fun clickEvent() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)

        with(binding) {
            tvSigninSignupbtn.setOnClickListener {
                resultLauncher.launch(intentToSignUp)
            }

            tvSigninLoginbtn.setOnClickListener {
                shortToast(R.string.sb_signin_success)
                startActivity(intentToMain)
            }

            tvSigninAuto.setOnClickListener {
                val id = etSigninId.text.toString()
                val pw = etSigninPw.text.toString()
                initSharedPreferences(id, pw)
            }
        }
    }

    private fun initSharedPreferences(id: String, pw: String) {
        loginSharedPreferences = LoginSharedPreferences(this@SignInActivity)
        loginSharedPreferences.saveLoginInfo(
            LoginInfo(
                id = id,
                pw = pw
            )
        )
    }

    private fun startLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                setText(result)
            }
    }

    private fun setText(result: ActivityResult) {
        if (result.resultCode == Activity.RESULT_OK) {
            binding.etSigninId.setText(result.data?.getStringExtra("id"))
            binding.etSigninPw.setText(result.data?.getStringExtra("pw"))
        }
    }
}
