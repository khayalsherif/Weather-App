package com.example.weatherapp.utils

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible


fun View.gone() {
    isGone = true
}

fun View.visible() {
    isVisible = true
}

fun View.inVisible() {
    isInvisible = true
}
