package com.sopt.dive.presentation.ui.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileRoute(
    paddingValues: PaddingValues
) {
    ProfileScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Profile"
        )
    }
}