package com.sopt.dive.presentation.ui.signup

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getString
import androidx.navigation.NavController
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.R
import com.sopt.dive.util.PrefsConst

@Composable
fun SignupRoute(
    paddingValues: PaddingValues,
    navController: NavController
) {

    SignupScreen(paddingValues = paddingValues) {
        navController.popBackStack()
    }
}


@Composable
fun SignupScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    finishSignup: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    var idText by remember { mutableStateOf("") }
    var pwText by remember { mutableStateOf("") }
    var nickNameText by remember { mutableStateOf("") }
    var drinkText by remember { mutableStateOf("") }

    LaunchedEffect(key1 = keyboardHeight) {
        scrollState.scrollBy(keyboardHeight.toFloat())
    }

    Scaffold(
        bottomBar = {
            Button(
                onClick = {
                    if(checkValidation(
                            context = context,
                            id = idText,
                            pw = pwText,
                            nickName = nickNameText))
                    {
                        prefs.setData(PrefsConst.ID_DATA, idText)
                        prefs.setData(PrefsConst.PW_DATA, pwText)
                        prefs.setData(PrefsConst.NICKNAME_DATA, nickNameText)
                        prefs.setData(PrefsConst.DRINK_DATA, drinkText)

                        finishSignup()
                    }
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .imePadding()
                    .navigationBarsPadding()
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                content = {
                    Text(
                        text = stringResource(R.string.signup_button),
                        color = Color.White
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Text(
                text = stringResource(R.string.signup_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.weight(0.2f))

            /** ID **/
            Text(
                text = stringResource(R.string.signup_id_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = idText,
                onValueChange = { idText = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                placeholder = {
                    Text(text = stringResource(R.string.signup_id_hint))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            /** PW **/
            Text(
                text = stringResource(R.string.signup_pw_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = pwText,
                onValueChange = { pwText = it },
                visualTransformation = PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                placeholder = {
                    Text(text = stringResource(R.string.signup_pw_hint))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            /** NickName **/
            Text(
                text = stringResource(R.string.signup_nickname_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = nickNameText,
                onValueChange = { nickNameText = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                placeholder = {
                    Text(text = stringResource(R.string.signup_nickname_hint))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            /** Drink **/
            Text(
                text = stringResource(R.string.signup_drink_title),
                fontSize = 28.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )

            TextField(
                value = drinkText,
                onValueChange = { drinkText = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedPlaceholderColor = Color.LightGray
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                }),
                placeholder = {
                    Text(text = stringResource(R.string.signup_drink_hint))
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(32.dp))

            Spacer(Modifier.weight(1f))
        }
    }
}

private fun checkValidation(
    context: Context,
    id: String,
    pw: String,
    nickName: String
): Boolean {
    val messageRes = when {
        id.isEmpty() || pw.isEmpty() || nickName.isEmpty() ->
            R.string.toast_signup_unavailable
        id.length !in 6..10 ->
            R.string.toast_signup_id_unavailable
        pw.length !in 8..12 ->
            R.string.toast_signup_pw_unavailable
        nickName.isEmpty() ->
            R.string.toast_signup_nickname_unavailable
        else -> null
    }

    return if (messageRes != null) {
        Toast.makeText(context, getString(context, messageRes), Toast.LENGTH_SHORT).show()
        false
    } else {
        true
    }
}

@Preview
@Composable
private fun PreviewSignUp() {
    SignupScreen(finishSignup = {})
}