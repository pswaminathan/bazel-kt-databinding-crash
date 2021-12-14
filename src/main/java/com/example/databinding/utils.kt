package com.example.databinding

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View

fun View.getFont(id: Int): Typeface? {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        resources.getFont(id)
    } else {
        androidx.core.content.res.ResourcesCompat.getFont(context, id)
    }
}

fun Context.getFont(id: Int): Typeface? {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        resources.getFont(id)
    } else {
        androidx.core.content.res.ResourcesCompat.getFont(this, id)
    }
}


private val displayMetrics = Resources.getSystem().displayMetrics
fun Number.dpToPx() = this.toFloat() * displayMetrics.density
fun Number.spToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), displayMetrics)
