package org.sopt.sample.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.sopt.sample.R
import org.sopt.sample.databinding.ActivityMainBinding
import org.sopt.sample.presentation.home.HomeFragment
import org.sopt.sample.presentation.mypage.MyPageFragment
import org.sopt.sample.presentation.search.SearchActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavi()
    }

    private fun setBottomNavi() { // 바텀네비게이션 세팅
        binding.bnvMainCustomnav.setOnItemSelectedListener {
            val transaction = supportFragmentManager.beginTransaction()
            when (it.itemId) {
                R.id.meun_home -> transaction.replace(R.id.fcv_main, HomeFragment())
                R.id.meun_mypage -> transaction.replace(R.id.fcv_main, MyPageFragment())
            }

            transaction.commit()
            true
        }

        binding.ivMainSearch.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }


}
