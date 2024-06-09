package com.example.welling.screen


import android.util.Log
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

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel

import com.example.welling.R
import com.example.welling.ui.theme.WellingTheme
import com.example.welling.component.TabItem
import com.example.welling.component.BottomNavigationBar




// 후원 화면

data class DonationItemData(
    val imageRes: Int,
    val title: String,
    val progress: Float,
    val progressText: String,
    val money: String
)

@Composable
fun Main_Donation_Screen(navController: NavController, mainViewModel: MainViewModel) {
    val donationItems = listOf(
        DonationItemData(
            imageRes = R.drawable.donation_1,
            title = "불공정무역 청소년을 위한 기부",
            money = "\$85000 (미국 달러 기준)",
            progress = 0.55f,
            progressText = "55%"
        ),
        DonationItemData(
            imageRes = R.drawable.grandma,
            title = "지속가능한 요양사 선생님을 모집합니다 (서울)",
            money = "\$45000 (미국 달러 기준)",
            progress = 0.40f,
            progressText = "40%"
        ),
        DonationItemData(
            imageRes = R.drawable.water,
            title = "협동조합을 통한 지속가능 목표 달성에 대한 기부",
            money = "\$35000 (미국 달러 기준)",
            progress = 0.75f,
            progressText = "75%"
        ),
        DonationItemData(
            imageRes = R.drawable.goat,
            title = "동물 보호를 위한 국제 협회 지원 사업 재능 봉사",
            money = "\$105000 (미국 달러 기준)",
            progress = 0.10f,
            progressText = "10%"
        )
    )

    Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Donation_Header()
            Spacer(modifier = Modifier.height(30.dp))
            Donation_FeaturedStory(navController)
            Spacer(modifier = Modifier.height(45.dp))
            Donation_Categories(mainViewModel)
            Spacer(modifier = Modifier.height(20.dp))
            Donation_Recommendation()
            Spacer(modifier = Modifier.height(16.dp))

            donationItems.forEach { item ->
                Main_DonationItem(
                    imageRes = item.imageRes,
                    title = item.title,
                    money = item.money,
                    progress = item.progress,
                    progressText = item.progressText
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

@Composable
fun Donation_Categories(mainViewModel: MainViewModel) {
    val categories = listOf("가난", "성평등", "재능 기부", "빈곤", "쓰레기")
    val selectedCategories = mainViewModel.selectedCategories
    val orderedCategories = selectedCategories + categories.filter { it !in selectedCategories }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        orderedCategories.forEach { category ->
            Donation_CategoryButton(
                text = category,
                isSelected = category in selectedCategories,
                onClick = { mainViewModel.selectCategory(category) }
            )
        }
    }
}

@Composable
fun Donation_CategoryButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFFF1F7DE) else Color(0xFFF2F4F5)
    val textColor = if (isSelected) Color(0xFFA4D41C) else Color(0xFF090A0A)

    Box(
        modifier = Modifier
            .height(32.dp)
            .background(backgroundColor, shape = RoundedCornerShape(32.dp))
            .padding(horizontal = 16.dp, vertical = 5.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = textColor
        )
    }
}

@Composable
fun Main_DonationItem(imageRes: Int, title: String, money: String, progress: Float, progressText: String) {
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
                text = money,
                fontSize = 12.sp,
                color = Color(0xFF303437),
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





@Composable
fun Donation_Header() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
        Text(
            text = "오늘도 ___을 기부해볼까요?",
            fontSize = 14.sp,
            lineHeight = 14.sp
        )
        Spacer(
            modifier = Modifier
                .width(375.dp)
                .height(1.dp)
                .background(Color(0xFFF2F4F5))
        )
    }
}

@Composable
fun Donation_FeaturedStory(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "떠오르는 이야기",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 18.sp
            )
            Text(
                text = "모두보기",
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
            Article_Component(navController = navController, imageRes = R.drawable.banner, title = "폐지와 동전을 줍는 6살 서연이의 따뜻한 겨울나기를 도와주세요", description = "서연이")
            Article_Component(navController = navController, imageRes = R.drawable.card, title = "굶주린 아이들을 위해 후원 바랍니다", description = "굶주린")
        }
    }
}

@Composable
fun Donation_Recommendation() {
    Column {
        Text(
            text = "후원 추천",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Donation_TabRow()
        //Spacer(modifier = Modifier.height(16.dp))
        //Donation_Item()
    }
}

@Composable
fun Donation_TabRow() {
    var selectedTab by remember { mutableStateOf("인기 목록") }

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TabItem(text = "인기 목록", isSelected = selectedTab == "인기 목록") { selectedTab = "인기 목록" }
        TabItem(text = "국내 후원", isSelected = selectedTab == "국내 후원") { selectedTab = "국내 후원" }
        TabItem(text = "국외 후원", isSelected = selectedTab == "국외 후원") { selectedTab = "국외 후원" }
    }
}

@Composable
fun Article_Component(
    navController: NavController,
    imageRes: Int,
    title: String,
    description: String = "안녕하세요"
){
    Column(
        modifier = Modifier
            .width(327.dp)
            .clickable {
                Log.d("클릭", "click")
                navController.navigate("article_screen/$imageRes/$title/$description")
            }
    ) {
        Image(
            painter = painterResource(R.drawable.banner), // 첫 번째 이미지
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            textAlign = TextAlign.Left,
            lineHeight = 24.sp
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color(0xFFF2F4F5))
        )
    }
}



@Preview(showBackground = true, device = "spec:width=375dp,height=812dp")
@Composable
fun Main_DefaultPreview() {
    WellingTheme {
        val navController = rememberNavController()
        val mainViewModel = MainViewModel()

        Main_Donation_Screen(navController = navController, mainViewModel = mainViewModel)
    }
}