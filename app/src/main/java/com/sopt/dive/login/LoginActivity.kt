package com.sopt.dive.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.MainActivity
import com.sopt.dive.R
import com.sopt.dive.signup.SignUpActivity
import com.sopt.dive.util.noRippleClickable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : ComponentActivity() {

    private var answerId = ""
    private var answerPw = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }

            Scaffold(
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { paddingValues ->
                Contents(modifier = Modifier.padding(paddingValues)) {
                    lifecycleScope.launch {
                        snackBarHostState.showSnackbar(
                            message = resources.getString(R.string.toast_login_success),
                            duration = SnackbarDuration.Short
                        )
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun Contents(
        modifier: Modifier = Modifier,
        loginSuccessCallback: () -> Unit
    ) {
        val focusManager = LocalFocusManager.current

        var idText by remember { mutableStateOf("") }
        var pwText by remember { mutableStateOf("") }

        val signupActivityResult = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if(result.resultCode == RESULT_OK) {
                answerId = result.data?.getStringExtra("id") ?: ""
                answerPw = result.data?.getStringExtra("pw") ?: ""

                Toast.makeText(this, this.resources.getString(R.string.toast_signup_success), Toast.LENGTH_SHORT).show()
            }
        }


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
                                val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                                signupActivityResult.launch(intent)
                            }
                    )

                    Spacer(Modifier.height(8.dp))

                    Button(
                        onClick = {
                            if(idText == answerId && pwText == answerPw) {
                                loginSuccessCallback()
                            } else {

                            }
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
                modifier = modifier
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
                    value = idText,
                    onValueChange = { idText = it },
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
                    value = pwText,
                    onValueChange = { pwText = it },
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

    @Preview(showBackground = true)
    @Composable
    private fun Preview() {
        Contents() {}
    }
}