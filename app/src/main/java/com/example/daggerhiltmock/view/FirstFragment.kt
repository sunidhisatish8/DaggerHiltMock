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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.daggerhiltmock.R
import com.example.daggerhiltmock.databinding.FragmentFirstBinding
import com.example.daggerhiltmock.viewModel.ProductViewModel
import com.example.daggerhiltmock.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: SharedViewModel
    private val handler = Handler(Looper.getMainLooper())
    private var debounceRunnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel._secondFragment.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.firstText.setText(it)
            }
        }
        binding.firstText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                debounceRunnable?.let {
                    handler.removeCallbacks(it)
                }
                debounceRunnable = Runnable {
                    viewModel.setTextFromFirstFragment(p0.toString())
                }

                handler.postDelayed(debounceRunnable!!, 300)
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        val data = arguments?.getString("firstData")

        return binding.root

    }
}