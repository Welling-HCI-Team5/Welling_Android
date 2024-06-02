package com.example.welling.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.welling.ui.components.DonationAmountSelector
import com.example.welling.ui.components.ProgressIndicator

@Composable
fun DonationProgressScreen() {
    val donationAmounts = listOf(5, 10, 15, 25)
    var selectedAmount by remember { mutableStateOf(donationAmounts[1]) }
    val regularityOptions = listOf("이번 한 번만 할게요", "매달", "3달 마다 한 번")
    var selectedRegularity by remember { mutableStateOf(regularityOptions[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "공정무역 청소년을 위한 기부",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        ProgressIndicator(
            progress = 0.3f,
            labelText = "기부 진행 상황",
            amountText = "$190.00",
            remainingDaysText = "15일 남았어요"
        )
        Text(
            text = "기부 금액",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        DonationAmountSelector(
            donationAmounts = donationAmounts,
            selectedAmount = selectedAmount,
            onAmountSelected = { selectedAmount = it }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "기부 정기성 입력",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            regularityOptions.forEach { option ->
                val isSelected = selectedRegularity == option
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else Color.White,
                    border = BorderStroke(1.dp, if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray),
                    modifier = Modifier
                        .clickable { selectedRegularity = option }
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        fontSize = 16.sp,
                        color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { /* TODO: 기부 완료 로직 추가 */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "기부 완료하러 가기",
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}
