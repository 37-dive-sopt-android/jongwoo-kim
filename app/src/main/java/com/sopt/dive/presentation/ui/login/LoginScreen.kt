package com.sopt.dive.presentation.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.R
import com.sopt.dive.presentation.ui.login.state.LoginAccountState
import com.sopt.dive.presentation.ui.login.state.rememberLoginAccountState
import com.sopt.dive.util.PrefsConst
import com.sopt.dive.util.noRippleClickable

@Composable
fun LoginRoute(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit = {},
    navigateToSignUp: () -> Unit = {}
) {
    val accountState = rememberLoginAccountState()
    val answerId = prefs.getData(PrefsConst.ID_DATA)
    val answerPw = prefs.getData(PrefsConst.PW_DATA)

    LoginScreen(
        paddingValues = paddingValues,
        accountState = accountState,
        onSignUpClick = {
            navigateToSignUp()
        },
        onLoginClick = {
            if(accountState.id == answerId && accountState.pw == answerPw) {
                navigateToHome()
            }
        }
    )
}


@Composable
fun LoginScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    accountState: LoginAccountState = rememberLoginAccountState(),
    onSignUpClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .imePadding()
                    .navigationBarsPadding()
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.login_signup_button),
                    fontSize = 12.sp,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .noRippleClickable {
                            onSignUpClick()
                        }
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        onLoginClick()
                    },
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .height(40.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.Black,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    ),
                    enabled = accountState.isLoginBtnEnable,
                    content = {
                        Text(
                            text = stringResource(R.string.login_button),
                            color = Color.White
                        )
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = stringResource(R.string.login_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.weight(0.2f))

            /** ID **/
            Text(
                text = stringResource(R.string.login_id_title),
                fontSize = 32.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = accountState.id,
                onValueChange = { accountState.id = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                placeholder = {
                    Text(
                        text = stringResource(R.string.login_id_hint)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            /** PW **/
            Text(
                text = stringResource(R.string.login_pw_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = accountState.pw,
                onValueChange = { accountState.pw = it },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                placeholder = {
                    Text(
                        text = stringResource(R.string.login_pw_hint)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))


        }
    }
}

@Preview
@Composable
private fun PreviewLogin() {
    LoginScreen()
}