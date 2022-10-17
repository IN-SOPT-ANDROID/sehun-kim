package org.sopt.sample.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.snackBar(message: Int) { // snackBar 확장함수
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun View.anchorSnackBar(message: Int) { // 위치 지정 snackBar 확장함수
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).apply {
        anchorView = this@anchorSnackBar
    }.show()
}
