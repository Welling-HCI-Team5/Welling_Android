package com.example.welling.Screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.welling.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationProgressScreen(navController: NavHostController) {
    val donationAmounts = listOf(5, 15, 25)
    val selectedAmount = remember { mutableStateOf<Int?>(null) }
    val regularityOptions = listOf("이번 한 번만 할게요", "매달", "3달 마다 한 번")
    val selectedRegularity = remember { mutableStateOf(regularityOptions[0]) }
    var customAmount by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { /* TODO: 기부 완료 로직 추가 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8BC34A))
            ) {
                Text(
                    text = "기부 완료하러 가기",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "공정무역 청소년을 위한 기부",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$190.00",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "15일 남았어요",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF8BC34A)),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
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
                    color = Color(0xFF8BC34A)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "30%",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "기부 금액",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp)
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
                leadingIcon = { Text(text = "$", style = MaterialTheme.typography.bodyMedium) },
                singleLine = true
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
                        color = if (isSelected) Color(0xFFCCFF90) else Color.White,
                        border = BorderStroke(1.dp, if (isSelected) Color(0xFF8BC34A) else Color.LightGray),
                        modifier = Modifier
                            .clickable {
                                customAmount = ""
                                selectedAmount.value = amount
                            }
                    ) {
                        Text(
                            text = "$$amount.00",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontSize = 16.sp,
                            color = if (isSelected) Color(0xFF8BC34A) else Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "기부 정기성 입력",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(vertical = 8.dp)
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
                        color = if (isSelected) Color(0xFFCCFF90) else Color.White,
                        border = BorderStroke(1.dp, if (isSelected) Color(0xFF8BC34A) else Color.LightGray),
                        modifier = Modifier
                            .clickable { selectedRegularity.value = option }
                    ) {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontSize = 16.sp,
                            color = if (isSelected) Color(0xFF8BC34A) else Color.Black
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
