package com.example.databinding

import android.graphics.*


private val mat = Matrix()
private val path = Path()
private val arrowPaint = Paint().apply {
    isAntiAlias = true
    style = Paint.Style.STROKE
    strokeWidth = 1.5.dpToPx()
    color = Color.WHITE
}

fun Canvas.drawArrow(right: Float, centerY: Float, upFactor: Float) {
    val arrowHeight = 3.dpToPx()
    val arrowWidthHalf = arrowHeight * 2
    val yBase = centerY - arrowHeight
    val yTip = centerY + arrowHeight
    path.moveTo(right, yBase)
    path.lineTo(right - arrowWidthHalf, yTip)
    path.lineTo(right - arrowWidthHalf * 2, yBase)
    mat.setRotate(-upFactor * 180f, right - arrowWidthHalf, centerY)
    path.transform(mat)
    drawPath(path, arrowPaint)
    path.rewind()
}
