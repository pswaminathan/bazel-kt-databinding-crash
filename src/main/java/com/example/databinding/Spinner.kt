package com.example.databinding

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity.CENTER_VERTICAL
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewOutlineProvider
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.databinding.example.lib.R
import com.databinding.example.*
import java.util.*
import kotlin.math.max
import kotlin.math.min

class Spinner<T: Enum<T>> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var selectedItem: T? = null
        set (value) {
            field = value
            invalidate()
        }

    private val values by lazy {
        (selectedItem!! as Enum<T>).javaClass.getMethod("values").invoke(null) as Array<T>
    }

    private var selectionListener: InverseBindingListener? = null

    fun selectedItemAttrChanged(l: InverseBindingListener) {
        selectionListener = l
    }

    private val optionNames: List<String>
    private val open = SimpleAnim(scale = 500f)
    private var isOpen = false
    set(value) {
        field = value
        invalidate()
        open.to(if (value) 1f else 0f)
        if (value) scroll.isVisible = true
    }
    private val argbEvaluator = ArgbEvaluator()
    private val openColor: Int
    private val closedColor: Int
    private val background = Paint().apply {
        isAntiAlias = true
        color = 0xff0a0f1b.toInt()
    }
    private val collapsedHeight = 51.dpToPx().toInt()
    private val collapsedBounds = Rect()
    private val expandedBounds = Rect()
    private val bounds = Rect()
    private val rect = Rect()

    private val title = Paint().apply {
        typeface = getFont(R.font.trade_gothic)
        isAntiAlias = true
        color = Color.WHITE
        textSize = 15.spToPx()
        letterSpacing = 0.08f
    }
    private val label = Paint().apply {
        typeface = getFont(R.font.pressura_light)
        isAntiAlias = true
        color = 0x88ffffff.toInt()
        textSize = 15.spToPx()
        letterSpacing = 0.025f
    }

    private val scroll: ScrollView
    private val itemHeight = 79.dpToPx().toInt()
    private val clipTop: Int

    init {
        setWillNotDraw(false)
        context.theme.obtainStyledAttributes(attrs, R.styleable.Spinner, 0, 0).let {
            try {
                optionNames = it.getTextArray(R.styleable.Spinner_android_entries).map { c -> c.toString() }
                closedColor = it.getColor(R.styleable.Spinner_closedColor, 0xff0a0f1b.toInt())
                openColor = it.getColor(R.styleable.Spinner_openColor, 0xff0a0f1b.toInt())
                clipTop = it.getDimension(R.styleable.Spinner_clipTop, 0f).toInt()
            } finally {
                it.recycle()
            }
        }

        val ll = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
        scroll = ScrollView(context).apply {
            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            overScrollMode = OVER_SCROLL_NEVER
            addView(ll)
        }

        addView(scroll)

        val f = getFont(R.font.pressura_light)
        optionNames.forEachIndexed { index, name ->
            ll.addView(TextView(context).apply {
                text = name
                layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, itemHeight)
                setTextColor(Color.WHITE)
                setTypeface(f, Typeface.NORMAL)
                textSize = 15f
                letterSpacing = 0.05f
                gravity = CENTER_VERTICAL
                setPadding(34.dpToPx().toInt(), 0, 0, 0)
                background = ResourcesCompat.getDrawable(resources,
                    if (index > 0)  R.drawable.button_transparent_with_border else R.drawable.button_transparent,
                    null)
                setOnClickListener {
                    selectedItem = values[index]
                    selectionListener?.onChange()
                    isOpen = false
                }
            })
        }

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val contentHeight = optionNames.size * itemHeight
        collapsedBounds.set(paddingStart, paddingTop, width - paddingEnd, paddingTop + collapsedHeight)
        expandedBounds.set(paddingStart, paddingTop, width - paddingEnd,
            min(paddingTop + collapsedHeight + contentHeight, height - paddingBottom))

        scroll.layout(
            expandedBounds.left,
            collapsedBounds.bottom,
            expandedBounds.right,
            expandedBounds.bottom,
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (open.animating) invalidate()
        val open = open.eased
        if (!isOpen && open < 0.0001f) {
            scroll.isVisible = false
        }

        // Opacity fill when open
        if (open > 0f) {
            canvas.drawColor(((open * 0xb2).toInt() shl 24) or 0x00f9f3ec)
        }

        if (!isOpen && clipTop > 0) {
            canvas.save()
            canvas.clipRect(0, 106.dpToPx().toInt(), width, height)
        }

        // Background box
        bounds.set(
            mix(collapsedBounds.left, expandedBounds.left, open),
            mix(collapsedBounds.top, expandedBounds.top, open),
            mix(collapsedBounds.right, expandedBounds.right, open),
            mix(collapsedBounds.bottom, expandedBounds.bottom, open)
        )
        rect.set(0, 0, bounds.width(), max(1, bounds.height() - collapsedHeight))
        scroll.clipBounds = rect
        val r = 4f.dpToPx()
        background.color = argbEvaluator.evaluate(open, closedColor, openColor) as Int
        canvas.drawRoundRect(
            bounds.left.toFloat(),
            bounds.top.toFloat(),
            bounds.right.toFloat(),
            bounds.bottom.toFloat(),
            r, r, background)

        // Label
        canvas.drawText("Show:", paddingStart + 19.dpToPx(), paddingTop + 30.dpToPx(), label)
        canvas.drawText(optionNames[selectedItem?.ordinal ?: 0].toUpperCase(Locale.getDefault()),
            paddingStart + 64.dpToPx(), paddingTop + 30.dpToPx(), title)

        // Arrow
        canvas.drawArrow(
            (collapsedBounds.right - paddingEnd).toFloat(),
            collapsedBounds.centerY().toFloat(),
            open)

        if (!isOpen && clipTop > 0) {
            canvas.restore()
        }
    }

    private var allowClose = false

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (!isOpen && event.y < clipTop) return false
        val inBox = bounds.contains(event.x.toInt(), event.y.toInt())
        if (event.action == MotionEvent.ACTION_DOWN) {
            allowClose = isOpen && (!inBox || collapsedBounds.contains(event.x.toInt(), event.y.toInt()))
            when {
                isOpen && !inBox -> return true
                inBox && !isOpen -> return true
                !isOpen && !inBox -> return false
            }
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (isOpen) {
                if (allowClose) {
                    isOpen = false
                    return true
                }
            } else if (inBox) {
                isOpen = true
                return true
            }
        }
        return if (isOpen) {
            // Only allow interacting with children when we're open.
            super.dispatchTouchEvent(event)
            // Capture all events when we're open.
            true
        } else {
            false
        }

    }
}
