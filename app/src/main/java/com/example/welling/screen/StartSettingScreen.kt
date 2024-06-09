package com.example.welling.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel
import com.example.welling.R
import com.example.welling.component.BtnMainColor
import com.example.welling.component.CustomTextBox


@Composable
fun StartSettingScreen(navController: NavHostController, mainViewModel: MainViewModel) {
    val isFirstLaunchCompleted by mainViewModel.isFirstLaunchCompleted

    if (!isFirstLaunchCompleted) {
        val categories = listOf(
            listOf("가난", "성평등"),
            listOf("노인", "빈곤"),
            listOf("재능 기부"),
            listOf("깨끗한 물", "쓰레기 오염")
        )
        val selectedCategories = mainViewModel.selectedCategories

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .statusBarsPadding()
        ) {
            CustomTextBox(
                text = "관심 분야 설정",
                colorId = R.color.black,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                textOnClick = null,
                spacerHeight = 8.dp
            )
            CustomTextBox(
                text = "관심 있는 분야를 선택해주세요.",
                colorId = R.color.gray_6c7072,
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
                                color = if (isSelected) colorResource(id = R.color.light_green) else colorResource(
                                    id = R.color.white
                                ),
                                border = BorderStroke(
                                    1.dp,
                                    if (isSelected) colorResource(id = R.color.light_green) else colorResource(
                                        id = R.color.gray_F2F4F5
                                    ).copy(alpha = 0.5f)
                                ),
                                modifier = Modifier
                                    .padding(7.dp)
                                    .clickable {
                                        mainViewModel.selectCategory(category)
                                    }
                            ) {
                                Text(
                                    text = category,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    ),
                                    fontSize = 16.sp,
                                    color = if (isSelected) colorResource(id = R.color.main_color_deep) else colorResource(
                                        id = R.color.black
                                    )
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
                    navController.navigate("main_donation")
                }
            )
        }
    } else {
        navController.navigate("main_donation")
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewStartSettingScreen() {
    val navController = rememberNavController()
    val mainViewModel = MainViewModel()

    StartSettingScreen(navController = navController, mainViewModel = mainViewModel)
}