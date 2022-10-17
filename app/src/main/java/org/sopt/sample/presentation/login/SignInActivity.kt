package org.sopt.sample.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
    private val loginSharedPreferences by lazy { LoginSharedPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLauncher()
        clickEvent()
        checkAutoLogin()
    }

    private fun clickEvent() {
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)
        val id = binding.etSigninId.text.toString()
        val pw = binding.etSigninPw.text.toString()

        with(binding) {
            tvSigninSignupbtn.setOnClickListener {
                resultLauncher.launch(intentToSignUp)
            }

            tvSigninLoginbtn.setOnClickListener {
                if (etSigninId.text.isEmpty() or etSigninPw.text.isEmpty()) {
                    shortToast(R.string.tm_signin_login)
                } else {
                    if (cbSigninCheckbox.isChecked) {
                        setSharedPreferences(id, pw, cbSigninCheckbox.isChecked)
                        startActivity(intentToMain)
                    } else {
                        shortToast(R.string.sb_signin_success)
                        startActivity(intentToMain)
                    }
                }
            }

            cbSigninCheckbox.setOnClickListener {
                cbSigninCheckbox.isChecked = cbSigninCheckbox.isChecked
                if (cbSigninCheckbox.isChecked) {
                    shortToast(R.string.tm_signin_autoon)
                } else shortToast(R.string.tm_signin_autooff)
            }
        }
    }

    private fun setSharedPreferences(id: String, pw: String, value: Boolean) {
        Log.d("******value******", "$value")
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

    private fun checkAutoLogin() {
        val intentToMain = Intent(this, MainActivity::class.java)

        if (loginSharedPreferences.getAutoLogin(this)) {
            shortToast(R.string.sb_signin_success)
            startActivity(intentToMain)
            finish()
        }
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
