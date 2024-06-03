package com.example.welling.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.ui.theme.WellingTheme
import com.example.welling.component.TabItem
import com.example.welling.component.BottomNavigationBar

data class AbilityItemData(
    val imageRes: Int,
    val title: String,
    val description: String,
    val progress: Float,
    val progressText: String
)

@Composable
fun Ability_Donation_Screen(navController: NavHostController) {
    val donationItems = listOf(
        AbilityItemData(
            imageRes = R.drawable.smile,
            title = "아프리카에서 한국어 선생님을 모집합니다",
            description = "아프리카 케냐 (항공 지원)",
            progress = 0.55f,
            progressText = "55%"
        ),
        AbilityItemData(
            imageRes = R.drawable.grandma,
            title = "지속가능한 요양사 선생님을 모집합니다 (서울)",
            description = "서울시 송파구 (사랑나눔재단)",
            progress = 0.40f,
            progressText = "40%"
        ),
        AbilityItemData(
            imageRes = R.drawable.water,
            title = "협동조합을 통한 지속가능 목표 달성에 대한 기부",
            description = "온라인",
            progress = 0.75f,
            progressText = "75%"
        ),
        AbilityItemData(
            imageRes = R.drawable.goat,
            title = "동물 보호를 위한 국제 협회 지원 사업 재능 봉사",
            description = "호주 시드니",
            progress = 0.10f,
            progressText = "10%"
        )
    )

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                .padding(16.dp)
        ) {
            Ability_Header()
            Spacer(modifier = Modifier.height(30.dp))
            Ability_NeedHelp(navController)
            Spacer(modifier = Modifier.height(25.dp))
            Ability_Categories()
            Spacer(modifier = Modifier.height(20.dp))
            Ability_DonationRecommendation()
            Spacer(modifier = Modifier.height(16.dp))

            donationItems.forEach { item ->
                Ability_DonationItem(
                    imageRes = item.imageRes,
                    title = item.title,
                    description = item.description,
                    progress = item.progress,
                    progressText = item.progressText
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

// 재능 기부
@Composable
fun Ability_Header() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(R.drawable.union), // 로고 이미지 추가
                contentDescription = null,
                modifier = Modifier
                    .width(30.4.dp)
                    .height(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "welling",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp,
                modifier = Modifier
                    .width(287.dp)
                    .height(36.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "재능 기부자를 찾고 있어요!",
                fontSize = 14.sp,
                lineHeight = 16.sp,
                textAlign = TextAlign.Start
            )
            Spacer(
                modifier = Modifier
                    .width(375.dp)
                    .height(1.dp)
                    .background(Color(0xFFF2F4F5))
            )
        }
    }
}

@Composable
fun Ability_NeedHelp(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "도움이 필요해요",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )
            Text(
                text = "모두 보기",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(end = 12.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()), // 수평 스크롤 가능하게 설정
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .width(301.dp)
                    .clickable { navController.navigate("donation_detail_screen") }
            ) {
                Image(
                    painter = painterResource(R.drawable.help), // 첫 번째 이미지
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(158.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "경기 광주",
                    fontSize = 12.sp,
                    color = Color(0xFFA4D41C)
                )
                Text(
                    text = "다문화 가정, 미혼모 자녀들의 교습소에서",
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "일일 선생님을 모십니다",
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFF2F4F5))
                )
            }
            Column(
                modifier = Modifier
                    .width(301.dp)
                    .clickable { navController.navigate("donation_detail_screen") }
            ) {
                Image(
                    painter = painterResource(R.drawable.card), // 두 번째 이미지
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(158.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "굶주린 아이들을 위해 후원 바랍니다",
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFF2F4F5))
                )
            }
        }
    }
}

@Composable
fun Ability_Categories() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        Ability_CategoryButton("국제")
        Ability_CategoryButton("예체능")
        Ability_CategoryButton("자원 봉사")
        Ability_CategoryButton("교육")
        Ability_CategoryButton("IT & 사회")
    }
}

@Composable
fun Ability_CategoryButton(text: String) {
    val backgroundColor = if (text == "국제") Color(0xFFF1F7DE) else Color(0xFFF2F4F5)
    val textColor = if (text == "국제") Color(0xFFA4D41C) else Color(0xFF090A0A)

    Box(
        modifier = Modifier
            .height(32.dp)
            .background(backgroundColor, shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp, vertical = 5.dp)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun Ability_DonationRecommendation() {
    Column {
        Text(
            text = "재능 기부 모집",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Ability_TabRow()
        Spacer(modifier = Modifier.height(8.dp))
        // Ability_DonationItem()은 리스트에서 반복되므로 제거합니다
    }
}

@Composable
fun Ability_TabRow() {
    var selectedTab by remember { mutableStateOf("인기 목록") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TabItem(text = "인기 목록", isSelected = selectedTab == "인기 목록") { selectedTab = "인기 목록" }
        TabItem(text = "국내 모집", isSelected = selectedTab == "국내 모집") { selectedTab = "국내 모집" }
        TabItem(text = "국외 모집", isSelected = selectedTab == "국외 모집") { selectedTab = "국외 모집" }
    }
}

@Composable
fun Ability_DonationItem(imageRes: Int, title: String, description: String, progress: Float, progressText: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(imageRes), // 아이템 이미지
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )
            Text(
                text = description,
                fontSize = 12.sp,
                color = Color(0xFF84B105),
                fontWeight = FontWeight.Medium,
                lineHeight = 16.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .width(176.dp)
                        .height(11.dp),
                    color = Color(0xFFA4D41C)
                )
                Text(
                    text = progressText,
                    color = Color(0xFF6C7072),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }
    }
}



@Preview(showBackground = true, device = "spec:width=375dp,height=1164dp")
@Composable
fun Ability_DefaultPreview() {
    WellingTheme {
        Ability_Donation_Screen(rememberNavController())
    }
}