package com.example.welling.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel
import com.example.welling.Component.BtnMainColor
import com.example.welling.Component.CustomTextBox
import com.example.welling.R

@Composable
fun StartSettingScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val categories = listOf(
        listOf("가난", "성평등"),
        listOf("노인", "빈곤"),
        listOf("재능 기부"),
        listOf("깨끗한 물", "쓰레기 오염")
    )
    val selectedCategories = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomTextBox(
            text = "관심 분야 설정",
            colorId = R.color.black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
            textOnClick = null,
            spacerHeight = 8.dp
        )
        CustomTextBox(
            text = "관심 있는 분야를 선택해주세요.",
            colorId = R.color.gray_e3e5e5,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_regular)),
            textOnClick = null,
            spacerHeight = 32.dp
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            categories.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    rowItems.forEach { category ->
                        val isSelected = selectedCategories.contains(category)
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = if (isSelected) Color(0xFFCCFF90) else Color.White,
                            border = BorderStroke(1.dp, if (isSelected) Color(0xFFCCFF90) else Color.LightGray.copy(alpha = 0.5f)),
                            modifier = Modifier
                                .padding(7.dp)
                                .clickable {
                                    if (isSelected) {
                                        selectedCategories.remove(category)
                                    } else {
                                        selectedCategories.add(category)
                                    }
                                }
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                fontSize = 16.sp,
                                color = if (isSelected) Color(0xFF8BC34A) else Color.Black
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BtnMainColor(
            text = "다음",
            onClick = {
                mainViewModel.setFirstLaunchCompleted()
                navController.navigate("main")
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartSettingScreen() {
    StartSettingScreen(navController = rememberNavController(), mainViewModel = MainViewModel())
}
