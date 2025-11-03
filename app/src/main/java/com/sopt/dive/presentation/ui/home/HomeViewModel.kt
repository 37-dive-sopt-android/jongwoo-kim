package com.sopt.dive.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.MyApplication.Companion.prefs
import com.sopt.dive.core.state.UiState
import com.sopt.dive.domain.model.friend.FriendInfoData
import com.sopt.dive.domain.model.user.UserInfoData
import com.sopt.dive.presentation.ui.home.state.HomeUiState
import com.sopt.dive.util.PrefsConst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    private val _userInfoLoadState = MutableStateFlow<UiState<UserInfoData>>(UiState.Loading)
    private val _friendListLoadState = MutableStateFlow<UiState<List<FriendInfoData>>>(UiState.Loading)

    val homeUiState: StateFlow<HomeUiState> = combine(
        _userInfoLoadState,
        _friendListLoadState,
    ) { userInfo, friendList ->
        HomeUiState(
            userInfoLoadState = userInfo,
            friendListLoadState = friendList,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(2000),
        initialValue = HomeUiState(
            userInfoLoadState = UiState.Loading,
            friendListLoadState = UiState.Loading,
        )
    )


    fun getUserInfo() = viewModelScope.launch {
        val dummyUserInfo = UserInfoData(
            id = prefs.getData(PrefsConst.ID_DATA) ?: "",
            pw = prefs.getData(PrefsConst.PW_DATA) ?: "",
            nickname = prefs.getData(PrefsConst.NICKNAME_DATA) ?: "",
            drink = prefs.getData(PrefsConst.DRINK_DATA) ?: ""
        )

        _userInfoLoadState.value = UiState.Success(dummyUserInfo)
    }

    fun getFriendList() = viewModelScope.launch {
        val dummyFriendList = listOf(
            FriendInfoData(
                id = "0",
                nickname = "김종우",
                profileImageUrl = "",
                description = "집가고싶다",
                birthDate = "2003-11-10"
            ),
            FriendInfoData(
                id = "1",
                nickname = "송민서",
                profileImageUrl = "",
                description = "너네 다 조용히 해",
                birthDate = "2001-10-28"
            ),
            FriendInfoData(
                id = "2",
                nickname = "이지현",
                profileImageUrl = "",
                description = "나는야 이제 A시드",
                birthDate = "2001-10-31"
            ),
            FriendInfoData(
                id = "3",
                nickname = "김민지",
                profileImageUrl = "",
                description = "잼얘 중독자",
                birthDate = "2001-11-01"
            ),
        )

        _friendListLoadState.value = UiState.Success(dummyFriendList)
    }
}