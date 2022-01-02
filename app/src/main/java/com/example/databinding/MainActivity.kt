package com.example.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.databinding.lib.R
import com.example.databinding.lib.databinding.ActivityMainBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}

object MyBindingAdapters {
    @BindingAdapter("animateVisibility")
    @JvmStatic
    fun animateVisibility(view: View, show: Boolean) {
        when {
            show && view.visibility == View.GONE -> with(view) {
                alpha = 0f
                visibility = View.VISIBLE
                animate().alpha(1f).start()
            }
            !show && view.visibility == View.VISIBLE -> view.animate().apply {
                alpha(0f)
                withEndAction { view.visibility == View.GONE }
                start()
            }
        }
    }
}
