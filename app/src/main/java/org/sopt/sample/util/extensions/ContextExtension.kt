package org.sopt.sample.util

import android.content.Context
import android.widget.Toast
import kotlin.math.roundToInt

fun Context.shortToast(message: Int) { // 토스트 메시지 확장함수
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

// dp, pixel 변환 확장함수
fun Context.dpToPixel(dp: Int): Int =
    (dp * resources.displayMetrics.density).roundToInt()
