package com.example.todolist.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.core.NetworkResult
import com.example.todolist.core.loginResponse
import com.example.todolist.data.RetrofitService
import com.example.todolist.data.request.Register
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val apiService = RetrofitService.apiService

    private val _register: MutableLiveData<NetworkResult<loginResponse>> = MutableLiveData()
    val register: LiveData<NetworkResult<loginResponse>> = _register

    fun register(user: Register) {
        _register.value = NetworkResult.Loading()
        viewModelScope.launch {
            try {
                val response = apiService.registerUser(user)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _register.value = NetworkResult.Success(it)
                    }
                } else {
                    _register.value = NetworkResult.Error(response.message())
                }
            } catch (e: Exception) {
                _register.value = NetworkResult.Error(e.localizedMessage)
            }
        }
    }
}