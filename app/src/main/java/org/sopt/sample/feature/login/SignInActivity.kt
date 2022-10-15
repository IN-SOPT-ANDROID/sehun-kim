package org.sopt.sample.feature.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.databinding.ActivitySignInBinding
import org.sopt.sample.feature.home.MainActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val myData: Intent? = result.data

                binding.etSigninId.setText(myData?.getStringExtra("id"))
                binding.etSigninPw.setText(myData?.getStringExtra("pw"))
            }
        }

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
                resultLauncher.launch(intentToSignUp)
            }

            tvSigninLoginbtn.setOnClickListener {
                startActivity(intentToMain)
            }
        }
    }
}
