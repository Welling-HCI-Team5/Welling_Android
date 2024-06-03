package com.example.welling.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welling.R
import com.example.welling.Component.CustomTextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPageScreen() {
    var selectedPeriod by remember { mutableStateOf("최근 7일 기준") }
    val periodOptions = listOf("최근 7일 기준", "최근 30일 기준", "최근 1년 기준")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(147.dp)
                        .background(color = colorResource(id = R.color.gray_353535), shape = CircleShape),
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_avatar),
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(155.dp)
                        .graphicsLayer {
                            translationY = -1.dp.toPx()
                            translationX = 1.dp.toPx()
                        }
                )
            }
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    CustomTextBox(
                        text = "상이님, 안녕하세요!",
                        colorId = R.color.purple_5e5873,
                        fontSize = 18.55.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                    CustomTextBox(
                        text = "골드 메달을 땄어요",
                        colorId = R.color.gray_6E6B7B,
                        fontSize = 12.36.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        textOnClick = null,
                        spacerHeight = 24.dp
                    )
                    CustomTextBox(
                        text = "48도 웰링이 달성!",
                        colorId = R.color.main_color,
                        fontSize = 18.55.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_medal),
                    contentDescription = "Medal",
                    modifier = Modifier.size(101.96.dp),
                    alignment = Alignment.Center
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            {}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    CustomTextBox(
                        text = "여러분이 도운,",
                        colorId = R.color.gray_6E6B7B,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        textOnClick = null,
                        spacerHeight = 6.dp
                    )
                    CustomTextBox(
                        text = "2.4만",
                        colorId = R.color.purple_5e5873,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_people),
                    contentDescription = "People",
                    tint = colorResource(id = R.color.main_color),
                    modifier = Modifier.size(73.975.dp).padding(end = 27.975.dp)
                )
            }
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    CustomTextBox(
                        text = "144 프로그램",
                        colorId = R.color.purple_5e5873,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        textOnClick = null,
                        spacerHeight = 6.dp
                    )
                    CustomTextBox(
                        text = "진행한 프로그램 수",
                        colorId = R.color.gray_6E6B7B,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.ic_program),
                    contentDescription = "Program",
                    tint = colorResource(id = R.color.main_color),
                    modifier = Modifier.size(73.975.dp).padding(end = 27.975.dp)
                )
            }
        }
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(colorResource(id = R.color.white), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTextBox(
                        text = "나의 영향력",
                        colorId = R.color.gray_900,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                    Box {
                        ExposedDropdownMenuBox(
                            expanded = false,
                            onExpandedChange = {}
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                CustomTextBox(
                                    text = selectedPeriod,
                                    colorId = R.color.gray_900,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                    textOnClick = null,
                                    spacerHeight = 0.dp
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Icon",
                                    tint = colorResource(R.color.gray_900)
                                )
                            }
                            ExposedDropdownMenu(
                                expanded = false,
                                onDismissRequest = { /* TODO */ }
                            ) {
                                periodOptions.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = {
                                            CustomTextBox(
                                                text = selectionOption,
                                                colorId = R.color.gray_900,
                                                fontSize = 12.sp,
                                                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                                textOnClick = {
                                                    selectedPeriod = selectionOption
                                                },
                                                spacerHeight = 0.dp
                                            )
                                        },
                                        onClick = { /* TODO */ }
                                    )
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
                ProgressBarWithLabel(label = "가난", progress = 0.55f)
                Spacer(modifier = Modifier.height(24.dp))
                ProgressBarWithLabel(label = "빈곤", progress = 0.25f)
                Spacer(modifier = Modifier.height(24.dp))
                ProgressBarWithLabel(label = "교육", progress = 0.75f)
                Spacer(modifier = Modifier.height(24.dp))
                ProgressBarWithLabel(label = "성평등", progress = 0.50f)
            }
        }
    }
}

@Composable
fun ProgressBarWithLabel(label: String, progress: Float) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextBox(
                text = label,
                colorId = R.color.gray_090A0A,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                textOnClick = null,
                spacerHeight = 0.dp,
            )
            Spacer(modifier = Modifier.weight(1f))
            CustomTextBox(
                text = "${(progress * 100).toInt()}%",
                colorId = R.color.gray_090A0A,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                textOnClick = null,
                spacerHeight = 0.dp,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(10.dp),
            color = colorResource(id = R.color.main_color),
            strokeCap  = StrokeCap.Round
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen()
}
