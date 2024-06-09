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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel
import com.example.welling.R
import com.example.welling.component.TabItem
import com.example.welling.ui.theme.WellingTheme


data class AbilityItemData(
    val imageRes: Int,
    val title: String,
    val description: String,
    val progress: Float,
    val progressText: String
)

@Composable
fun Ability_Donation_Screen(navController: NavController, mainViewModel: MainViewModel) {
    val nav = rememberNavController()
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Ability_Header()
        Spacer(modifier = Modifier.height(30.dp))
        Ability_NeedHelp(navController)
        Spacer(modifier = Modifier.height(25.dp))
        Ability_Categories(mainViewModel)
        Spacer(modifier = Modifier.height(20.dp))
        Ability_DonationRecommendation()
        Spacer(modifier = Modifier.height(16.dp))

        donationItems.forEach { item ->
            Ability_DonationItem(
                imageRes = item.imageRes,
                title = item.title,
                description = item.description,
                progress = item.progress,
                progressText = item.progressText,
                nav = navController
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Ability_Categories(mainViewModel: MainViewModel) {
    val categories = listOf("국제", "예체능", "자원 봉사", "교육", "IT & 사회")
    val selectedCategories = mainViewModel.selectedCategories
    val orderedCategories = selectedCategories + categories.filter { it !in selectedCategories }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.horizontalScroll(rememberScrollState())
    ) {
        orderedCategories.forEach { category ->
            Ability_CategoryButton(
                text = category,
                isSelected = category in selectedCategories,
                onClick = { mainViewModel.selectCategory(category) }
            )
        }
    }
}

@Composable
fun Ability_CategoryButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
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
fun Ability_NeedHelp(navController: NavController) {
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
            Ability_Component(
                navController = navController,
                imageRes = R.drawable.ability_world,
                location = "경기 광주",
                title = "다문화 가정, 미혼모 자녀들의 교습소에서\n일일 선생님을 모십니다",
                description = "안녕하세요, 저희는 다문화 가정과 미혼모 자녀들을 위한 교습소에서 일일 선생님으로 함께할 재능 기부자를 모집하고 있습니다.\n" +
                        "\n" +
                        "우리 교습소에서는 다양한 배경을 가진 아이들이 모여 꿈을 키워가고 있습니다. 하지만 이 아이들은 여전히 많은 교육적 지원이 필요합니다. 여러분의 소중한 재능과 시간을 통해 이 아이들에게 희망과 배움의 기회를 제공해 주실 수 있습니다.\n" +
                        "\n" +
                        "모집 분야는 다음과 같습니다:\n" +
                        "\n" +
                        "학습 지도 (국어, 수학, 영어 등)\n" +
                        "예술 활동 (미술, 음악, 춤 등)\n" +
                        "체육 활동 (축구, 농구, 체조 등)\n" +
                        "창의적 활동 (과학 실험, 만들기 등)\n" +
                        "재능 기부자의 자격 조건은 다음과 같습니다:\n" +
                        "\n" +
                        "아이들을 사랑하고, 교육에 열정이 있는 분\n" +
                        "주 1회 이상, 최소 2시간의 시간을 내실 수 있는 분\n" +
                        "해당 분야에 전문 지식이나 경험이 있으신 분\n" +
                        "재능 기부를 통해 아이들에게 밝은 미래를 선물해 주세요. 여러분의 작은 나눔이 큰 변화를 만들어낼 수 있습니다. 참여를 원하시는 분들은 아래 연락처로 문의해 주시기 바랍니다.\n" +
                        "\n" +
                        "문의 및 신청:\n" +
                        "이메일: volunteer@teachhope.org\n" +
                        "전화: 010-1234-5678\n" +
                        "\n" +
                        "많은 관심과 참여 부탁드립니다."
            )
            Ability_Component(
                navController = navController,
                imageRes = R.drawable.ability_senior,
                location = "서울 노원",
                title = "어르신들의 행복한 하루를 위해,\n재능 기부자를 모집합니다",
                description = "안녕하세요, 저희는 지역 사회의 어르신들이 더욱 풍요로운 일상을 보낼 수 있도록 다양한 프로그램을 운영하고 있는 복지센터입니다. 어르신들의 삶에 활력을 불어넣어 주실 재능 기부자분들을 모집하고 있습니다.\n" +
                        "\n" +
                        "어르신들은 다양한 이유로 사회와 단절되어 외로움을 느끼며 지내고 계십니다. 여러분의 따뜻한 마음과 재능이 어르신들에게 큰 힘과 위로가 될 수 있습니다. 여러분의 작은 나눔이 어르신들의 삶에 큰 변화를 가져올 것입니다.\n" +
                        "\n" +
                        "모집 분야는 다음과 같습니다:\n" +
                        "\n" +
                        "건강 체조 및 운동 지도: 간단한 체조나 스트레칭, 건강 운동을 통해 어르신들의 신체 건강을 돕는 프로그램\n" +
                        "취미 활동 지도: 미술, 공예, 음악, 가드닝 등 다양한 취미 활동을 통해 어르신들이 즐거운 시간을 보낼 수 있도록 돕는 프로그램\n" +
                        "IT 교육: 스마트폰, 컴퓨터 사용법 등 디지털 기기 활용법을 가르쳐 어르신들이 새로운 세상과 소통할 수 있도록 돕는 프로그램\n" +
                        "문화 교류: 다양한 문화 프로그램(전통 예술, 영화 감상, 독서 모임 등)을 통해 어르신들의 정서적 안정과 즐거움을 제공하는 프로그램\n" +
                        "재능 기부자의 자격 조건은 다음과 같습니다:\n" +
                        "\n" +
                        "어르신들을 사랑하고, 그들의 이야기에 귀 기울일 수 있는 분\n" +
                        "주 1회 이상, 최소 2시간의 시간을 내실 수 있는 분\n" +
                        "해당 분야에 전문 지식이나 경험이 있으신 분\n" +
                        "재능 기부를 통해 어르신들의 삶에 새로운 활력을 불어넣어 주세요. 여러분의 따뜻한 손길이 어르신들에게 큰 위로와 기쁨이 될 것입니다. 참여를 원하시는 분들은 아래 연락처로 문의해 주시기 바랍니다.\n" +
                        "\n" +
                        "문의 및 신청:\n" +
                        "이메일: volunteer@elderjoy.org\n" +
                        "전화: 010-9876-5432\n" +
                        "\n" +
                        "많은 관심과 참여 부탁드립니다."
            )
        }
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
fun Ability_DonationItem(
    imageRes: Int,
    title: String,
    description: String,
    progress: Float,
    progressText: String,
    nav: NavController
) {
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

@Composable
fun Ability_Component(
    navController: NavController,
    imageRes: Int,
    location: String, // ex) 경기 광주
    title: String, // ex) 다문화 가정, 미혼모 자녀들의 교습소에서
    description: String,
) {
    Column(
        modifier = Modifier
            .width(301.dp)
            .clickable {
                navController.navigate("donation_detail_screen/$imageRes/$title/$description")
            }
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(158.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = location,
            fontSize = 12.sp,
            color = Color(0xFFA4D41C)
        )
        Text(
            text = title,
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

@Preview(showBackground = true, device = "spec:width=375dp,height=1164dp")
@Composable
fun Ability_DefaultPreview() {
    WellingTheme {
        val navController = rememberNavController()
        val mainViewModel = MainViewModel()

        Ability_Donation_Screen(navController = navController, mainViewModel = mainViewModel)
    }
}