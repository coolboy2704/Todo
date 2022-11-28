package com.example.todolist.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.core.NetworkResult
import com.example.todolist.core.loginRequest
import com.example.todolist.core.loginResponse
import com.example.todolist.data.RetrofitService
import com.example.todolist.data.response.User
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val apiService = RetrofitService.apiService

    private val _login: MutableLiveData<NetworkResult<loginResponse>> = MutableLiveData()
    val login: LiveData<NetworkResult<loginResponse>> = _login

    fun login(user: loginRequest) {
        _login.value = NetworkResult.Loading()
        viewModelScope.launch {
            try {
                val response = apiService.login(user)

                if (response.isSuccessful) {
                    response.body()?.let {
                        _login.value = NetworkResult.Success(it)
                    }
                } else {
                    _login.value = NetworkResult.Error(response.message())
                }
            } catch (e: Exception) {
                _login.value = NetworkResult.Error(e.localizedMessage)
            }
        }
    }
}