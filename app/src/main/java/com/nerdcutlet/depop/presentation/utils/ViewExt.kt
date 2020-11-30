package com.nerdcutlet.depop.presentation.utils

import android.view.View
import android.view.ViewGroup

fun View.setMargin(
    leftMargin: Int = 0,
    topMargin: Int = 0,
    rightMargin: Int = 0,
    bottomMargin: Int = 0
) {
    val layoutParams = layoutParams as ViewGroup.MarginLayoutParams

    layoutParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin)
    setLayoutParams(layoutParams)
}
