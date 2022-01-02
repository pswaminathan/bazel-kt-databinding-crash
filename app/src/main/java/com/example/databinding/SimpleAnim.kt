package com.example.databinding

import android.view.animation.AccelerateDecelerateInterpolator
import androidx.dynamicanimation.animation.FloatValueHolder
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import kotlin.math.max
import kotlin.math.min

class SimpleAnim(initial: Float = 0f, private val maxValue: Float = 1f, private val scale: Float = 1000f) {
    private val scaleInv = 1f / scale
    val value get() = holder.value * scaleInv
    val animating get() = anim.isRunning
    val eased get() = ease.getInterpolation(value)

    private val holder = FloatValueHolder(min(1f, max(0f, initial)) * scale)
    private val anim = SpringAnimation(holder).also {
        val spring = SpringForce(holder.value)
        it.setMinValue(0f)
        it.setMaxValue(scale * maxValue)
        spring.dampingRatio = SpringForce.DAMPING_RATIO_NO_BOUNCY
        spring.stiffness = SpringForce.STIFFNESS_VERY_LOW
        it.spring = spring
    }
    fun to(value: Float) {
        anim.animateToFinalPosition(min(maxValue, max(0f, value)) * scale)
    }
    fun set(value: Float) {
        anim.cancel()
        holder.value = value * scale
    }
    companion object {
        private val ease = AccelerateDecelerateInterpolator()
    }
}
