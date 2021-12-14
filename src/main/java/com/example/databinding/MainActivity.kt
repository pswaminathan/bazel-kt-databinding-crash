package com.example.databinding

import androidx.databinding.DataBindingUtil
import android.widget.TextView
import android.app.Activity
import android.os.Bundle
import com.example.databinding.lib.R
import com.example.databinding.lib.databinding.ActivityMainBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
