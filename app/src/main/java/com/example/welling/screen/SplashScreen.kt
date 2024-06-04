package com.example.welling.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.welling.MainViewModel
import com.example.welling.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.main_color_khaki))
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_splash_circles),
            contentDescription = "Splash Circles",
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter),
            contentScale = ContentScale.FillWidth
        )
        Image(
            painter = painterResource(id = R.drawable.ic_ghost),
            contentDescription = "Splash Ghost",
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = "Welling",
            color = colorResource(id = R.color.main_color),
            fontSize = 32.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(0.dp, 0.dp, 0.dp, 30.dp)
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000) // 2초 동안 대기
        navController.navigate("start_setting_screen") {
            // 이전 화면은 스택에서 제거 => 뒤로가기 눌렀을 때 SplashScreen으로 돌아오지 않도록
            popUpTo("splash_screen") { inclusive = true }
        }
    }
}