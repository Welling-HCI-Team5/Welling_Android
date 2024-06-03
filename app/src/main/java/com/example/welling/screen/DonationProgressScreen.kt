package com.example.welling.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.component.BtnMainColor
import com.example.welling.component.CustomTextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationProgressScreen(navController: NavHostController) {
    val donationAmounts = listOf(5, 15, 25)
    val selectedAmount = remember { mutableStateOf<Int?>(null) }
    val regularityOptions = listOf("이번 한 번만 할게요", "매달", "3달 마다 한 번")
    val selectedRegularity = remember { mutableStateOf(regularityOptions[0]) }
    var customAmount by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = colorResource(id = R.color.white),
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(40.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CustomTextBox(
                            text = "오늘의 ",
                            colorId = R.color.black,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            textOnClick = null,
                            spacerHeight = 0.dp
                        )
                        CustomTextBox(
                            text = "웰링",
                            colorId = R.color.main_color,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            textOnClick = null,
                            spacerHeight = 0.dp
                        )
                        CustomTextBox(
                            text = " 완료!",
                            colorId = R.color.black,
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                            textOnClick = null,
                            spacerHeight = 0.dp
                        )
                    }
                    CustomTextBox(
                        text = "• 웰링 내역은 알림탭에서 확인 가능합니다.",
                        colorId = R.color.gray_6c7072,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                }
            }
        }
    }

    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(32.dp))
                CustomTextBox(
                    text = "공정무역 청소년을 위한 기부",
                    colorId = R.color.black,
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 8.dp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomTextBox(
                        text = "$190.00",
                        colorId = R.color.gray_6c7072,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                        textOnClick = null,
                        spacerHeight = 4.dp
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "Clock Icon",
                            tint = colorResource(id = R.color.main_color),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        CustomTextBox(
                            text = "15일 남았어요",
                            colorId = R.color.main_color,
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                            textOnClick = null,
                            spacerHeight = 4.dp
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    LinearProgressIndicator(
                        progress = 0.3f,
                        modifier = Modifier.weight(1f),
                        color = colorResource(id = R.color.main_color)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    CustomTextBox(
                        text = "30%",
                        colorId = R.color.gray_6c7072,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                        textOnClick = null,
                        spacerHeight = 0.dp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextBox(
                    text = "기부 금액",
                    colorId = R.color.black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    textOnClick = null,
                    spacerHeight = 8.dp
                )
                OutlinedTextField(
                    value = customAmount,
                    onValueChange = {
                        customAmount = it
                        selectedAmount.value = null
                    },
                    shape = RoundedCornerShape(12.dp),
                    label = { Text(text = "직접 금액 입력") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Text(text = "$", fontFamily = FontFamily(Font(R.font.pretendard_regular)), fontSize = 16.sp) },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(id = R.color.light_green),
                        unfocusedBorderColor = colorResource(id = R.color.gray_e3e5e5)
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    donationAmounts.forEach { amount ->
                        val isSelected = selectedAmount.value == amount
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) colorResource(id = R.color.light_green) else colorResource(
                                id = R.color.white
                            ),
                            border = BorderStroke(1.dp, if (isSelected) colorResource(id = R.color.light_green) else colorResource(id = R.color.gray_e3e5e5)),
                            modifier = Modifier
                                .clickable {
                                    customAmount = ""
                                    selectedAmount.value = amount
                                }
                        ) {
                            Text(
                                text = "$$amount.00",
                                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                fontSize = 16.sp,
                                color = if (isSelected) colorResource(id = R.color.main_color_deep) else colorResource(
                                    id = R.color.black
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextBox(
                    text = "기부 정기성 입력",
                    colorId = R.color.black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_bold)),
                    textOnClick = null,
                    spacerHeight = 8.dp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    regularityOptions.forEach { option ->
                        val isSelected = selectedRegularity.value == option
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) colorResource(id = R.color.light_green) else colorResource(id = R.color.white),
                            border = BorderStroke(1.dp, if (isSelected) colorResource(id = R.color.light_green) else colorResource(id = R.color.gray_e3e5e5)),
                            modifier = Modifier
                                .clickable { selectedRegularity.value = option }
                        ) {
                            Text(
                                text = option,
                                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                fontSize = 16.sp,
                                color = if (isSelected) colorResource(id = R.color.main_color_deep) else colorResource(id = R.color.black)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                BtnMainColor(
                    text = "기부 완료하러 가기",
                    onClick = { showDialog = true }
                )
            }
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.TopStart)
                    .padding(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier.size(56.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDonationProgressScreen() {
    DonationProgressScreen(navController = rememberNavController())
}
