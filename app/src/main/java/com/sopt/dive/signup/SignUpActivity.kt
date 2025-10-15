package com.sopt.dive.signup

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.util.MyApplication.Companion.prefs
import com.sopt.dive.util.PrefsConst

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold { paddingValues ->
                Contents(modifier = Modifier.padding(paddingValues))
            }
        }
    }

    @Composable
    private fun Contents(modifier: Modifier = Modifier) {
        var idText by remember { mutableStateOf("") }
        var pwText by remember { mutableStateOf("") }
        var nickNameText by remember { mutableStateOf("") }
        var drinkText by remember { mutableStateOf("") }


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 40.dp, horizontal = 16.dp)
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
                placeholder = {
                    Text(
                        text = stringResource(R.string.signup_id_hint)
                    )
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
                placeholder = {
                    Text(
                        text = stringResource(R.string.signup_pw_hint)
                    )
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
                placeholder = {
                    Text(
                        text = stringResource(R.string.signup_nickname_hint)
                    )
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
                placeholder = {
                    Text(
                        text = stringResource(R.string.signup_drink_hint)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    if(checkValidation(idText, pwText, nickNameText)) {
                        prefs.setData(PrefsConst.ID_DATA, idText)
                        prefs.setData(PrefsConst.PW_DATA, pwText)
                        prefs.setData(PrefsConst.NICKNAME_DATA, nickNameText)
                        prefs.setData(PrefsConst.DRINK_DATA, drinkText)

                        val finishIntent = Intent().apply {
                            putExtra("id", idText)
                            putExtra("pw", pwText)
                        }
                        setResult(RESULT_OK, finishIntent)
                        finish()
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
                        text = stringResource(R.string.signup_button),
                        color = Color.White
                    )
                }
            )
        }
    }
    
    private fun checkValidation(id: String, pw: String, nickName: String): Boolean {
        if(id.isEmpty() || pw.isEmpty() || nickName.isEmpty()) {
            Toast.makeText(this, this.resources.getString(R.string.toast_signup_unavailable), Toast.LENGTH_SHORT).show()
            return false
        } else if(id.length !in 6..10) {
            Toast.makeText(this, this.resources.getString(R.string.toast_signup_id_unavailable), Toast.LENGTH_SHORT).show()
            return false
        } else if(pw.length !in 8..12) {
            Toast.makeText(this, this.resources.getString(R.string.toast_signup_pw_unavailable), Toast.LENGTH_SHORT).show()
            return false
        } else if(nickName.isEmpty()) {
            Toast.makeText(this, this.resources.getString(R.string.toast_signup_nickname_unavailable), Toast.LENGTH_SHORT).show()
            return false
        } else {
            return true
        }
    }

    @Preview(showBackground = true)
    @Composable
    private fun PreviewContents() {
        Contents()
    }
}