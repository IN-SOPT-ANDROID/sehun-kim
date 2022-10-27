package org.sopt.sample.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.MainActivity
import org.sopt.sample.util.shortToast

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startLauncher()
        setListeners()
        checkAutoLogin()
    }

    private fun setListeners() { // setOnClickListener 이벤트 함수
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        val intentToMain = Intent(this, MainActivity::class.java)
        val id = binding.etSigninId.text.toString()
        val pw = binding.etSigninPw.text.toString()

        with(binding) {
            tvSigninSignupbtn.setOnClickListener {
                resultLauncher.launch(intentToSignUp)
            }

            tvSigninLoginbtn.setOnClickListener {
                if (etSigninId.text.isEmpty() or etSigninPw.text.isEmpty()) { // ID,PW 빈 텍스트 검사
                    shortToast(R.string.tm_signin_login)
                } else {

                    if (cbSigninCheckbox.isChecked) { // true(체크) -> setSharedPreferences 함수 호출
                        signInViewModel.setSharedPreferences(id, pw, cbSigninCheckbox.isChecked)
                        startActivity(intentToMain)
                    } else {
                        shortToast(R.string.sb_signin_success)
                        startActivity(intentToMain)
                    }
                }
            }

            cbSigninCheckbox.setOnClickListener {
                cbSigninCheckbox.isChecked = cbSigninCheckbox.isChecked // 자동로그인 체크 On/Off
                if (cbSigninCheckbox.isChecked) {
                    shortToast(R.string.tm_signin_autoon)
                } else shortToast(R.string.tm_signin_autooff)
            }
        }
    }

    private fun checkAutoLogin() { // 앱 시작 시, 자동로그인 체크 여부 확인
        val intentToMain = Intent(this, MainActivity::class.java)

        if (signInViewModel.checkAutoLogin()) {
            shortToast(R.string.sb_signin_success)
            startActivity(intentToMain)
            finish()
        }
    }

    private fun startLauncher() { // registerForActivity 초기화 -> setText 함수호출
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                setText(result)
            }
    }

    private fun setText(result: ActivityResult) { // SignUpActivity 데이터 텍스트 박스 지정
        if (result.resultCode == Activity.RESULT_OK) {
            binding.etSigninId.setText(result.data?.getStringExtra("id"))
            binding.etSigninPw.setText(result.data?.getStringExtra("pw"))
        }
    }
}
