package com.sopt.dive.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.R
import com.sopt.dive.MyApplication
import com.sopt.dive.util.PrefsConst
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }

            val idData = MyApplication.Companion.prefs.getData(PrefsConst.ID_DATA)
            val pwData = MyApplication.Companion.prefs.getData(PrefsConst.PW_DATA)
            val nickNameData = MyApplication.Companion.prefs.getData(PrefsConst.NICKNAME_DATA)
            val drinkData = MyApplication.Companion.prefs.getData(PrefsConst.DRINK_DATA)

            LaunchedEffect(Unit) {
                lifecycleScope.launch {
                    snackBarHostState.showSnackbar(
                        message = resources.getString(R.string.toast_login_success),
                        duration = SnackbarDuration.Short
                    )
                }
            }

            Scaffold(
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { paddingValues ->
                Contents(
                    modifier = Modifier.Companion.padding(paddingValues),
                    idData = idData.toString(),
                    pwData = pwData.toString(),
                    nickNameData = nickNameData.toString(),
                    drinkData = drinkData.toString()
                )
            }
        }
    }

    @Composable
    private fun Contents(
        modifier: Modifier = Modifier.Companion,
        idData: String = "",
        pwData: String = "",
        nickNameData: String = "",
        drinkData: String = "",
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.Companion.White)
                .padding(horizontal = 16.dp, vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.drawable.default_profile),
                    contentDescription = null,
                    modifier = Modifier.Companion.size(32.dp)
                )

                Spacer(Modifier.Companion.width(8.dp))

                Text(
                    text = nickNameData,
                    fontSize = 24.sp,
                    color = Color.Companion.Black,
                    fontWeight = FontWeight.Companion.Medium
                )
            }

            // ID
            Text(
                text = stringResource(R.string.main_id_title, idData),
                fontWeight = FontWeight.Companion.Medium,
                fontSize = 16.sp
            )

            // PW
            Text(
                text = stringResource(R.string.main_pw_title, pwData),
                fontWeight = FontWeight.Companion.Medium,
                fontSize = 16.sp
            )

            // NICKNAME
            Text(
                text = stringResource(R.string.main_nickname_title, nickNameData),
                fontWeight = FontWeight.Companion.Medium,
                fontSize = 16.sp
            )

            // DRINK
            Text(
                text = stringResource(R.string.main_drink_title, drinkData),
                fontWeight = FontWeight.Companion.Medium,
                fontSize = 16.sp
            )
        }
    }

    @Preview
    @Composable
    private fun PreviewContents() {
        Contents(
            idData = "aaaaa",
            pwData = "bbbbb",
            nickNameData = "ccccc",
            drinkData = "dddddd"
        )
    }
}