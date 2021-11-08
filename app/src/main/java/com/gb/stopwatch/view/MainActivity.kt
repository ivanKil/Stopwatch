package com.gb.stopwatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val vm: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm.init()
        vm.ldText.observe(this, ::setTextTimer1)

        binding.buttonStart.setOnClickListener { vm.start() }
        binding.buttonPause.setOnClickListener { vm.pause() }
        binding.buttonStop.setOnClickListener { vm.stop() }
    }

    fun setTextTimer1(text: String) {
        binding.textTime.text = text
    }
}

