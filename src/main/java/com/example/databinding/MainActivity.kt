package com.example.databinding

import android.databinding.DataBindingUtil
import android.widget.TextView
import android.app.Activity
import android.os.Bundle
import com.example.databinding.R
import com.example.databinding.databinding.ActivityMainBinding

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}
