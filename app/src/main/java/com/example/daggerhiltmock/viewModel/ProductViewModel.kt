package com.example.daggerhiltmock.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daggerhiltmock.UiState
import com.example.daggerhiltmock.model.data.PhoneDetailsRequest
import com.example.daggerhiltmock.model.data.PhoneDetailsResponse
import com.example.daggerhiltmock.model.interceptor.NetworkInterceptor
import com.example.daggerhiltmock.model.localRepository.ProductDetails
import com.example.daggerhiltmock.repository.IRepository
import com.example.daggerhiltmock.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: IRepository
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState<PhoneDetailsResponse>>()
    val uiState: LiveData<UiState<PhoneDetailsResponse>> = _uiState

    fun addPhoneDetails(phoneDetailsRequest: PhoneDetailsRequest) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val exists = repository.checkIfExists(phoneDetailsRequest.name)
                if (exists != null) {
                    _uiState.value = UiState.Error("Data already exists")
                } else {
                    val response = repository.addPhoneDetails(phoneDetailsRequest)
                    if (response.isSuccessful) {
                        response.body()?.let {
                            _uiState.value = UiState.Success(it)
                        } ?: _uiState.postValue(UiState.Error("Response body is null"))
                    } else {
                        _uiState.value = UiState.Error("Failed to save data")
                    }
                }
            } catch (e: NetworkInterceptor.NoInternetException) {
                _uiState.value = UiState.Error("Error: ${e.message}")
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Error: ${e.message}")
            }
        }
    }
}