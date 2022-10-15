package org.sopt.sample.util

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import kotlin.math.roundToInt

fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.dpToPixel(dp: Int): Int =
    (dp * resources.displayMetrics.density).roundToInt()

fun Int.dpToPixel(): Int = this * Resources.getSystem().displayMetrics.density.roundToInt()