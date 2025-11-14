package com.sopt.dive.presentation.ui.login.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

interface LoginAccountState {
    var name: String
    var pw: String

    val isLoginBtnEnable: Boolean
}

private class LoginAccountStateImpl() : LoginAccountState {
    override var name: String by mutableStateOf("")
    override var pw: String by mutableStateOf("")

    override val isLoginBtnEnable: Boolean
        get() = name.isNotEmpty() && pw.isNotEmpty()
}

@Composable
fun rememberLoginAccountState(): LoginAccountState = remember { LoginAccountStateImpl() }