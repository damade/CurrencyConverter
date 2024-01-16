package com.theseuntaylor.currencyconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.theseuntaylor.currencyconverter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(com.damilola.core_android.R.style.AppTheme)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }



}