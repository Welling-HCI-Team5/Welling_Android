package com.example.welling.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.welling.R
import com.example.welling.screen.Ability_Categories
import com.example.welling.screen.Ability_DonationItem
import com.example.welling.screen.Ability_DonationRecommendation
import com.example.welling.screen.Ability_Header
import com.example.welling.screen.Ability_NeedHelp
import com.example.welling.component.BottomNavigationBar

// DonationItemData, donationItems, LazyColumn 부분 이용
data class DonationItemData(
    val imageRes: Int,
    val title: String,
    val description: String,
    val progress: Float,
    val progressText: String
)

@Composable
fun Reuse_Screen(navController: NavHostController) {
    val donationItems = listOf(
        DonationItemData(
            imageRes = R.drawable.smile, // 이미지 삽입
            title = "아프리카에서 한국어 선생님을 모집합니다",
            description = "아프리카 케냐 (항공 지원)",
            progress = 0.55f,
            progressText = "55%"
        ),
        DonationItemData(
            imageRes = R.drawable.grandma,
            title = "지속가능한 요양사 선생님을 모집합니다 (서울)",
            description = "서울시 송파구 (사랑나눔재단)",
            progress = 0.40f,
            progressText = "40%"
        ),
        DonationItemData(
            imageRes = R.drawable.water,
            title = "협동조합을 통한 지속가능 목표 달성에 대한 기부",
            description = "온라인",
            progress = 0.75f,
            progressText = "75%"
        ),
        DonationItemData(
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
                .padding(16.dp)
        ) {
            Ability_Header() // 화면에서 원하는 순서
            Spacer(modifier = Modifier.height(30.dp))
            Ability_NeedHelp(navController)
            Spacer(modifier = Modifier.height(25.dp))
            Ability_Categories()
            Spacer(modifier = Modifier.height(20.dp))
            Ability_DonationRecommendation()
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn( // 스크롤 가능
                modifier = Modifier.fillMaxSize()
            ) {
                items(donationItems) { item ->
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
}