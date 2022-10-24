package com.example.databinding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Outline
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import com.example.databinding.lib.R
import com.example.databinding.dpToPx
import com.example.databinding.getFont
import com.example.databinding.spToPx
import com.example.otherpkg.MyModel

class IconToggleButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var text: String = ""
        set(value) {
            if (field != value) {
                field = value
                textWidth = textPaint.measureText(value).toInt()
                invalidate()
            }
        }

    var colorTheme: Int = 0
        set(value) {
            field = value
            refreshDrawableState()
            invalidate()
        }

    private var textWidth: Int = 0
    private val icon: Drawable
    private val textColor: ColorStateList
    private val iconPadding = 13.dpToPx().toInt()

    private val textPaint = Paint().apply {
        isAntiAlias = true
    }

    fun setObjects(l: List<MyModel>) {}

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.IconToggleButton, 0, 0).let {
            try {
                textColor = it.getColorStateList(R.styleable.IconToggleButton_android_textColor) ?: ColorStateList.valueOf(0xFF000000.toInt())
                icon = it.getDrawable(R.styleable.IconToggleButton_icon)!!
                icon.mutate()
                icon.setTintList(it.getColorStateList(R.styleable.IconToggleButton_android_drawableTint) ?: ColorStateList.valueOf(0xFF000000.toInt()))
                textPaint.letterSpacing = it.getFloat(R.styleable.IconToggleButton_android_letterSpacing, 0f)
                textPaint.textSize = it.getDimension(R.styleable.IconToggleButton_android_textSize, 15f)
                // textPaint.typeface = getFont(it.getResourceId(R.styleable.IconToggleButton_android_fontFamily, R.font.pressura_light))
                text = it.getString(R.styleable.IconToggleButton_android_text) ?: ""
                colorTheme = it.getInt(R.styleable.IconToggleButton_colorTheme, 0)
            } finally {
                it.recycle()
            }
        }
        outlineProvider = object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                background.getOutline(outline)
            }
        }
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        return super.onCreateDrawableState(extraSpace + 1).also {
            when (colorTheme) {
                1 -> mergeDrawableStates(it, intArrayOf(R.attr.themeDark))
                2 -> mergeDrawableStates(it, intArrayOf(R.attr.themeCream))
                else -> {}
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        val textX = (width - textWidth + iconPadding + icon.intrinsicWidth)/2
        val iconY = (height - icon.intrinsicHeight)/2

        icon.setBounds(
            textX - icon.intrinsicWidth - iconPadding,
            iconY,
            textX - iconPadding,
            iconY + icon.intrinsicHeight
        )
        icon.draw(canvas)

        canvas.drawText(
            text,
            textX.toFloat(),
            (height + textPaint.textSize) / 2 - 2,
            textPaint)
    }

    override fun drawableStateChanged() {
        super.drawableStateChanged()
        if (icon.setState(drawableState)) {
            invalidate()
        }
        val c = textColor.getColorForState(drawableState, 0)
        if (c != textPaint.color) {
            textPaint.color = c
            invalidate()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val h = MeasureSpec.getSize(heightMeasureSpec)
        val w = if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) {
            MeasureSpec.getSize(widthMeasureSpec)
        } else {
            textWidth + iconPadding + icon.intrinsicWidth + paddingLeft + paddingRight
        }
        setMeasuredDimension(w, h)
    }
}
