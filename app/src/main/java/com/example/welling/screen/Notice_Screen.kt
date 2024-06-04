package com.example.welling.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.welling.component.TabItem

@Composable
fun Notice_Screen(navController: NavHostController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            GreetingSection()
            Spacer(modifier = Modifier.height(16.dp))
            RecentWellingRecords()
        }
    }
}

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
fun RecentWellingRecords() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "최근 웰링 기록",
            fontSize = 18.55.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5E5873)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Welling_TabRow()
        Spacer(modifier = Modifier.height(16.dp))

        val dates = listOf("Oct 7", "Sep 15", "Oct 1", "Oct 3", "Oct 31", "Nov 17", "Jul 5")
        dates.forEach { date ->
            WellingRecordItem(date = date, title = "경기 광주\n미혼모 지원 센터", description = "다문화 가정, 미혼모 자녀들의 교습소..")
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Welling_TabRow() {
    var selectedTab by remember { mutableStateOf("기부") }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Well_TabItem(text = "기부", isSelected = selectedTab == "기부") { selectedTab = "기부" }
        Well_TabItem(text = "봉사", isSelected = selectedTab == "봉사") { selectedTab = "봉사" }
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