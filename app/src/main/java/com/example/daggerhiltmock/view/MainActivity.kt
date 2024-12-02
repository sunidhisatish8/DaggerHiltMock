package com.example.daggerhiltmock.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.daggerhiltmock.R
import com.example.daggerhiltmock.UiState
import com.example.daggerhiltmock.contentresolver.BookContract
import com.example.daggerhiltmock.contentresolver.BookContract.BOOK_TABLE_NAME
import com.example.daggerhiltmock.contentresolver.BookContract.COLUMN_BOOK_NAME
import com.example.daggerhiltmock.contentresolver.BookContract.COLUMN_ID
import com.example.daggerhiltmock.contentresolver.BookContract.COLUMN_YEAR_OF_PUBLIsH
import com.example.daggerhiltmock.databinding.ActivityMainBinding
import com.example.daggerhiltmock.model.data.Data
import com.example.daggerhiltmock.model.data.PhoneDetailsRequest
import com.example.daggerhiltmock.model.localRepository.ProductDetails
import com.example.daggerhiltmock.viewModel.ProductViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var phoneDetailsRequest: PhoneDetailsRequest
    private val viewModel: ProductViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.btnFetchList.setOnClickListener {
//            val projection = arrayOf(COLUMN_BOOK_NAME, COLUMN_ID, COLUMN_YEAR_OF_PUBLIsH)
//            val cursor = contentResolver.query(
//                BookContract.CONTENT_URI,
//                projection,
//                null,
//                null,
//                null,
//                null
//            )
//            cursor?.use {
//                while (it.moveToNext()) {
//                    val bookId = it.getInt(1)
//                    val bookName = it.getString(0)
//                    val yearOfPublish = it.getString(2)
//                    println("fetching book details $bookId, $bookName, $yearOfPublish")
//                }
//            }
//        }
        setUpData()
    }
val migration: (Int, Int) -> Int = {a, b -> a * b }
    private fun setUpData() {
       
        binding.btnSubmit.setOnClickListener {
            binding.apply {
                phoneDetailsRequest = PhoneDetailsRequest(
                    Data(
                        price = etPrice.text.toString().toDoubleOrNull() ?: 0.0,
                        year = etManufacturerYear.text.toString().toInt(),
                        cpuModel = etCpuModel.text.toString(),
                        hardDiskSize = etHardDiskSize.text.toString()
                    ),
                    name = etModelName.text.toString()
                )
                etPrice.text.clear()
                etCpuModel.text.clear()
                etHardDiskSize.text.clear()
                etModelName.text.clear()
                etManufacturerYear.text.clear()
            }
            viewModel.addPhoneDetails(phoneDetailsRequest)

            viewModel.uiState.observe(this) { state ->
                when (state) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is UiState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            this,
                            "Data has been saved successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}