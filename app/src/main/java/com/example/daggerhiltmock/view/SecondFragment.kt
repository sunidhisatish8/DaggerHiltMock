package com.example.daggerhiltmock.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.daggerhiltmock.R
import com.example.daggerhiltmock.databinding.FragmentFirstBinding
import com.example.daggerhiltmock.databinding.FragmentSecondBinding
import com.example.daggerhiltmock.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SharedViewModel
    private val handler = Handler(Looper.getMainLooper())
    private var debounceRunnable: Runnable? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel._firstFragment.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.secondText.setText(it)
            }
        }
        binding.secondText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                debounceRunnable?.let {
                    handler.removeCallbacks(it)
                }
                debounceRunnable = Runnable {
                    viewModel.setTextFromSecondFragment(p0.toString())
                }

                handler.postDelayed(debounceRunnable!!, 900)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
        return binding.root

    }
}