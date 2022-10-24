package com.example.databinding

import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import kotlin.math.max
import kotlin.math.min


fun mix(low: Int, high: Int, ratio: Float): Int          = low + ((high - low) * ratio).toInt()
fun mix(low: Float, high: Float, ratio: Float): Float    = low +  (high - low) * ratio
fun mix(low: Double, high: Double, ratio: Float): Double = low +  (high - low) * ratio
fun mix(low: Long, high: Long, ratio: Double): Long      = low + ((high - low) * ratio).toLong()
fun mix(low: Long, high: Long, ratio: Float): Long       = low + ((high - low) * ratio.toDouble()).toLong()
fun Float.clamp(low: Float = 0f, high: Float = 1f) = min(high, max(low, this))
fun Int.clamp(low: Int = 0, high: Int = 1) = min(high, max(low, this))


fun View.getFont(id: Int): Typeface? {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        resources.getFont(id)
    } else {
        ResourcesCompat.getFont(context, id)
    }
}

fun Context.getFont(id: Int): Typeface? {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        resources.getFont(id)
    } else {
        ResourcesCompat.getFont(this, id)
    }
}

object IconToggleButtonAdapters {
    @BindingAdapter("iconToggleTxt")
    @JvmStatic
    fun setTxt(view: IconToggleButton, txt: String) {
        view.text = txt
    }
}


private val displayMetrics = Resources.getSystem().displayMetrics
fun Number.dpToPx() = this.toFloat() * displayMetrics.density
fun Number.spToPx() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), displayMetrics)
