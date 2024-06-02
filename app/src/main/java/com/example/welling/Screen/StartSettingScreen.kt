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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.welling.MainViewModel

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
        Text(
            text = "관심 분야 설정",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.Start)
        )
        Text(
            text = "관심 있는 분야를 선택해주세요.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(bottom = 32.dp)
                .align(Alignment.Start),
            color = Color.Gray
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
                                .padding(horizontal = 6.dp, vertical = 6.dp)
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
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
                                fontSize = 16.sp,
                                color = if (isSelected) Color(0xFF8BC34A) else Color.Black
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                mainViewModel.setFirstLaunchCompleted()
                navController.navigate("main")
            },
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "다음",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
