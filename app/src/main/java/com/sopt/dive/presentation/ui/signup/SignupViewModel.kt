package com.sopt.dive.presentation.ui.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.core.state.UiState
import com.sopt.dive.data.dto.signup.SignupRequestDto
import com.sopt.dive.data.dto.user.UserInfoData
import com.sopt.dive.data.factory.ServicePool
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    private val _signupState = MutableStateFlow<UiState<UserInfoData>>(UiState.Init)
    val signupState = _signupState.asStateFlow()


    fun signup(request: SignupRequestDto) = viewModelScope.launch {
        val result = authService.signup(request)

        if(result.isSuccessful) {
            result.body()?.let {
                _signupState.emit(UiState.Success(it.data))
            }
        }
    }
}