package com.sopt.dive.presentation.ui.home.state

import com.sopt.dive.core.state.UiState
import com.sopt.dive.presentation.model.friend.FriendInfoData
import com.sopt.dive.presentation.model.user.UserInfoData

data class HomeUiState(
    val userInfoLoadState: UiState<UserInfoData> = UiState.Loading,
    val friendListLoadState: UiState<List<FriendInfoData>> = UiState.Loading,
) {
    val loadState: UiState<Unit>
        get() = when {
            userInfoLoadState is UiState.Loading &&
            friendListLoadState is UiState.Loading -> UiState.Loading

            userInfoLoadState is UiState.Failure ||
            friendListLoadState is UiState.Failure -> UiState.Failure("fail to load data")

            userInfoLoadState is UiState.Success &&
            friendListLoadState is UiState.Success -> UiState.Success(Unit)

            else -> UiState.Loading
        }
}