package com.sopt.dive.presentation.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.state.UiState
import com.sopt.dive.data.dto.login.LoginRequestDto
import com.sopt.dive.data.factory.ServicePool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _loginState = MutableStateFlow<UiState<Int>>(UiState.Init)
    val loginState = _loginState.asStateFlow()


    fun login(request: LoginRequestDto) = viewModelScope.launch {
        val result = authService.login(request)

        if(result.isSuccessful) {
            result.body()?.let {
                _loginState.emit(UiState.Success(it.data.userId))
            }
        }
    }
}