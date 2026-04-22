package com.dmj.fly.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    context?.showToast(message, duration)
}

fun <T> LifecycleOwner.collectFlow(flow: Flow<T>, state: Lifecycle.State = Lifecycle.State.STARTED, collector: suspend (T) -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(state) {
            flow.collect(collector)
        }
    }
}

fun Long.formatFileSize(): String {
    return when {
        this < 1024 -> "$this B"
        this < 1024 * 1024 -> "${this / 1024} KB"
        this < 1024 * 1024 * 1024 -> "${this / (1024 * 1024)} MB"
        else -> "${this / (1024 * 1024 * 1024)} GB"
    }
}

fun Long.formatDate(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
    val sdf = java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault())
    return sdf.format(java.util.Date(this))
}

fun Float.coerceInRange(min: Float, max: Float): Float {
    return this.coerceIn(min, max)
}

fun Double.format(digits: Int = 6): String {
    return "%.${digits}f".format(this)
}