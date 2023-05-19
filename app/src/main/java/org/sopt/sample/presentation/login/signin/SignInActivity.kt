package org.sopt.sample.presentation.login.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.presentation.login.signup.SignUpActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val signInViewModel: SignInViewModel by viewModels()
    private val autoLogin = Observer<Boolean> {
        binding.cbSigninCheckbox.isChecked = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = this
        signInViewModel.isChecked.observe(this, autoLogin)

        setContentView(binding.root)
        startLauncher()
        setListeners()
    }

    private fun setListeners() { // setOnClickListener 이벤트 함수
        val intentToSignUp = Intent(this, SignUpActivity::class.java)
        binding.tvSigninSignupbtn.setOnClickListener {
            resultLauncher.launch(intentToSignUp)
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
