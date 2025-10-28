package com.sopt.dive.presentation.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.sopt.dive.R
import com.sopt.dive.core.state.UiState
import com.sopt.dive.presentation.model.friend.FriendInfoData
import com.sopt.dive.presentation.model.user.UserInfoData
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getUserInfo()
        viewModel.getFriendList()
    }

    when (homeUiState.loadState) {
        is UiState.Success -> {
            val userInfo = (homeUiState.userInfoLoadState as? UiState.Success)?.data
            val friendList = (homeUiState.friendListLoadState as? UiState.Success)?.data ?: listOf()

            if (userInfo != null) {
                HomeScreen(
                    paddingValues = paddingValues,
                    homeUserInformation = userInfo,
                    friendList = friendList
                )
            }
        }
        is UiState.Failure -> {}
        is UiState.Loading -> {}
        else -> {}
    }
}

@Composable
fun HomeScreen(
    homeUserInformation: UserInfoData,
    paddingValues: PaddingValues = PaddingValues(0.dp),
    friendList: List<FriendInfoData> = emptyList()
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.default_profile),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = homeUserInformation.nickname,
                        fontSize = 24.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(Modifier.height(16.dp))

                // ID
                Text(
                    text = stringResource(R.string.main_id_title, homeUserInformation.id),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                // NICKNAME
                Text(
                    text = stringResource(R.string.main_nickname_title, homeUserInformation.nickname),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                // DRINK
                Text(
                    text = stringResource(R.string.main_drink_title, homeUserInformation.drink),
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )
            }

            Spacer(Modifier.height(32.dp))
        }
        
        items(
            items = friendList,
            key = { it.id }
        ) { friendData ->
            FriendItemLayout(friendData)
        }
    }
}

@Composable
private fun FriendItemLayout(friendInfo: FriendInfoData) {
    val (_, currentMonth, currentDay) = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).split("-")
    val (_, birthMonth, birthDay) = friendInfo.birthDate.split("-")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = if(friendInfo.profileImageUrl.isBlank()) painterResource(id = R.drawable.default_profile) else rememberAsyncImagePainter(friendInfo.profileImageUrl),
            contentDescription = null
        )

        Spacer(Modifier.width(10.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // nickname
                Text(
                    text = friendInfo.nickname,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp
                )

                Spacer(Modifier.width(6.dp))

                if(currentMonth == birthMonth && currentDay == birthDay) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_birthday),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(Modifier.height(4.dp))

            // description
            Text(
                text = friendInfo.description,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHome() {
    val homeUserInformation = UserInfoData(
        id = "ididid",
        pw = "pwpwpwpw",
        nickname = "nicknick",
        drink = "drinkdrink"
    )

    HomeScreen(
        homeUserInformation = homeUserInformation
    )
}