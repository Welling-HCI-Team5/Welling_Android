package com.example.welling.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.ui.theme.WellingTheme
import com.example.welling.component.BottomNavigationBar


@Composable
fun Notice_Screen(navController: NavHostController) {
    var selectedTab by remember { mutableStateOf("기부") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        GreetingSection()
        Spacer(modifier = Modifier.height(16.dp))
        Welling_TabRow(selectedTab, onTabSelected = { selectedTab = it })
        Spacer(modifier = Modifier.height(16.dp))

        if (selectedTab == "기부") {
            DonationRecords()
        } else {
            VolunteerRecords()
        }
    }
}

@Composable
fun DonationRecords() {
    val donationRecords = listOf(
        RecordData("Oct 7", "경기 광주\n미혼모 지원 센터", "다문화 가정, 미혼모 자녀들의 교습소.."),
        RecordData("Sep 15", "서울시 강남구\n노인 요양 센터", "노인분들을 위한 요양 서비스 제공.."),
        RecordData("Oct 1", "부산시 해운대구\n아동 복지 센터", "아동들의 복지를 위한 후원 및 지원.."),
        RecordData("Oct 10", "대구시 수성구\n지역아동센터", "취약계층 아동들에게 교육 지원.."),
        RecordData("Sep 25", "광주시 북구\n청소년 쉼터", "위기 청소년들을 위한 쉼터 지원.."),
        RecordData("Oct 14", "울산시 남구\n장애인 복지관", "장애인들을 위한 복지 서비스 제공.."),
        RecordData("Oct 20", "대전시 동구\n저소득층 지원 센터", "저소득층 가정 지원 및 복지 서비스..")
    )

    donationRecords.forEach { record ->
        WellingRecordItem(record.date, record.title, record.description)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun VolunteerRecords() {
    val volunteerRecords = listOf(
        RecordData("Oct 3", "서울시 강서구\n환경 정화 활동", "쓰레기 수거 및 환경 보호 활동.."),
        RecordData("Oct 31", "인천시 중구\n노숙자 지원 활동", "노숙자분들에게 음식과 의복 지원.."),
        RecordData("Nov 17", "대전시 서구\n동물 보호소 지원", "유기 동물 보호 및 지원 활동.."),
        RecordData("Nov 1", "부산시 북구\n노인 돌봄 서비스", "노인분들을 위한 방문 돌봄 서비스.."),
        RecordData("Oct 21", "광주시 서구\n아동 돌봄 서비스", "취약계층 아동들을 위한 돌봄 서비스.."),
        RecordData("Oct 15", "울산시 중구\n공원 정화 활동", "지역 공원 내 쓰레기 수거 및 정화.."),
        RecordData("Nov 10", "대구시 서구\n무료 급식 봉사", "지역 주민들을 위한 무료 급식 제공..")
    )

    volunteerRecords.forEach { record ->
        WellingRecordItem(record.date, record.title, record.description)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Welling_TabRow(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Well_TabItem(text = "기부", isSelected = selectedTab == "기부") { onTabSelected("기부") }
        Well_TabItem(text = "봉사", isSelected = selectedTab == "봉사") { onTabSelected("봉사") }
    }
}

data class RecordData(
    val date: String,
    val title: String,
    val description: String
)

@Composable
fun GreetingSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF7F7F7))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                val text = "상이님, 오늘 웰링 하셨나요?"
                val highlightWord = "웰링"
                val annotatedString = buildAnnotatedString {
                    val startIndex = text.indexOf(highlightWord)
                    append(text.substring(0, startIndex))
                    withStyle(style = SpanStyle(color = Color(0xFFA4D41C))) {
                        append(highlightWord)
                    }
                    append(text.substring(startIndex + highlightWord.length))
                }

                Text(
                    text = annotatedString,
                    fontSize = 18.55.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5E5873)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "오늘도 놓치지 마세요!",
                    fontSize = 12.36.sp,
                    color = Color(0xFF6E6B7B)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "아무것도 하지 않았어요",
                    fontSize = 16.55.sp,
                    color = Color(0xFF84B105)
                )
            }
            Spacer(modifier = Modifier.width(35.dp))
            Image(
                painter = painterResource(id = R.drawable.union),
                contentDescription = "Union Image",
                modifier = Modifier
                    .width(46.42.dp)
                    .height(53.44.dp)
            )
        }
    }
}

@Composable
fun Well_TabItem(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            color = if (isSelected) Color(0xFF84B105) else Color(0xFF72777A),
            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
            fontSize = 16.2.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .width(141.dp)
                .height(1.dp)
                .background(if (isSelected) Color(0xFF84B105) else Color.Transparent)
        )
    }
}

@Composable
fun WellingRecordItem(date: String, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = date.split(" ")[0],
                fontSize = 14.sp,
                color = Color(0xFF84B105)
            )
            Text(
                text = date.split(" ")[1],
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF84B105)
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        Column {
            Text(
                text = title,
                fontSize = 14.2.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(id = R.drawable.union), contentDescription = null, tint = Color(0xFF84B105), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = description,
                    fontSize = 12.sp,
                    color = Color(0xFF999999)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Notice_Preview() {
    WellingTheme {
        Notice_Screen(rememberNavController())
    }
}