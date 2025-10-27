package com.sopt.dive.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.R
import com.sopt.dive.presentation.ui.home.data.HomeUserInfoData
import com.sopt.dive.util.PrefsConst

@Composable
fun HomeRoute(
    paddingValues: PaddingValues
) {
    val homeUserInformation = HomeUserInfoData(
        id = prefs.getData(PrefsConst.ID_DATA) ?: "",
        pw = prefs.getData(PrefsConst.PW_DATA) ?: "",
        nickname = prefs.getData(PrefsConst.NICKNAME_DATA) ?: "",
        drink = prefs.getData(PrefsConst.DRINK_DATA) ?: ""
    )

    HomeScreen(
        paddingValues = paddingValues,
        homeUserInformation = homeUserInformation
    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    homeUserInformation: HomeUserInfoData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Companion.White)
            .padding(paddingValues)
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
                text = homeUserInformation.nickname,
                fontSize = 24.sp,
                color = Color.Companion.Black,
                fontWeight = FontWeight.Companion.Medium
            )
        }

        // ID
        Text(
            text = stringResource(R.string.main_id_title, homeUserInformation.id),
            fontWeight = FontWeight.Companion.Medium,
            fontSize = 16.sp
        )

        // PW
        Text(
            text = stringResource(R.string.main_pw_title, homeUserInformation.pw),
            fontWeight = FontWeight.Companion.Medium,
            fontSize = 16.sp
        )

        // NICKNAME
        Text(
            text = stringResource(R.string.main_nickname_title, homeUserInformation.nickname),
            fontWeight = FontWeight.Companion.Medium,
            fontSize = 16.sp
        )

        // DRINK
        Text(
            text = stringResource(R.string.main_drink_title, homeUserInformation.drink),
            fontWeight = FontWeight.Companion.Medium,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
private fun PreviewHome() {
    val homeUserInformation = HomeUserInfoData(
        id = "ididid",
        pw = "pwpwpwpw",
        nickname = "nicknick",
        drink = "drinkdrink"
    )

    HomeScreen(
        homeUserInformation = homeUserInformation
    )
}