package com.shid.template.app.utils.extensions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.shid.template.R


inline fun <reified VM : ViewModel> AppCompatActivity.viewModelOf(
    factory: ViewModelProvider.Factory
) = ViewModelProvider(this, factory)[VM::class.java]


internal fun Activity.showSnackbar(
    view: View,
    message: String,
    isError: Boolean = false,
    duration: Int = 0
) {
    val sb = Snackbar.make(view, message, duration)

    if (isError) {
        sb.setBackgroundTint(loadColor(R.color.purple_200))
            .setTextColor(loadColor(R.color.white))
            .show()
    } else {
        sb.setBackgroundTint(loadColor(R.color.purple_200))
            .setTextColor(loadColor(R.color.white))
            .show()
    }

    if (duration == -2) {
        Handler(Looper.getMainLooper()).postDelayed({
            sb.dismiss()
        }, 10000)
    }

}

fun Activity.setTransparentStatusBar(isLightStatusBar: Boolean = true) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = Color.TRANSPARENT
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isLightStatusBar) {
        var flags: Int = window.decorView.systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.decorView.systemUiVisibility = flags
    }
}