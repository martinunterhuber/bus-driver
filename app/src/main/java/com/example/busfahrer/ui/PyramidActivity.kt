package com.example.busfahrer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.busfahrer.databinding.ActivityPyramidBinding

class PyramidActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPyramidBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPyramidBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}