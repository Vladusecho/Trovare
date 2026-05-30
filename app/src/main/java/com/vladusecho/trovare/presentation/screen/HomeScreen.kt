package com.vladusecho.trovare.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.vladusecho.trovare.R
import com.vladusecho.trovare.presentation.ui.theme.TrovareTheme
import com.vladusecho.trovare.presentation.ui.theme.TrovareTypography
import com.vladusecho.trovare.presentation.viewModel.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    onSearchClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff979797).copy(alpha = 0.1f))
                .height(300.dp),
        ) {

        }
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 64.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_movie),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ФИЛЬМЫ",
                    color = Color.Gray,
                    fontFamily = TrovareTypography,
                    fontWeight = FontWeight.Black,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Что вы хотите\nпосмотреть сегодня?",
                    color = Color.Black,
                    fontFamily = TrovareTypography,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    )
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun HomeScreenPreview() {
    TrovareTheme {
        HomeScreen(
            paddingValues = PaddingValues(),
        ) { }
    }
}

