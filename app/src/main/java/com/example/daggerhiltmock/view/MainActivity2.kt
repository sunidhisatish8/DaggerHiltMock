package com.example.daggerhiltmock.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltmock.R
import com.example.daggerhiltmock.databinding.ActivityMain2Binding
import com.example.daggerhiltmock.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        viewModel._secondFragment.observe(this) {
            if (it.isNotEmpty()) {
                binding.textActivity.text = it
            }
        }
        viewModel._firstFragment.observe(this) {
            if (it.isNotEmpty()) {
                binding.textActivity.text = it
            }
        }


        val fragments = listOf(FirstFragment(), SecondFragment())
        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = adapter
    }
}