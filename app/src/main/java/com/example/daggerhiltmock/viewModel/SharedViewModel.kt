package com.example.daggerhiltmock.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var _firstFragment = MutableLiveData<String>()
    var _secondFragment = MutableLiveData<String>()

    fun setTextFromFirstFragment(value: String) {
        _firstFragment.value = value
    }

    fun setTextFromSecondFragment(value: String) {
        _secondFragment.value = value

    }
}