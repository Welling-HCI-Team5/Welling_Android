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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel
import com.example.welling.R
import com.example.welling.component.TabItem
import com.example.welling.ui.theme.WellingTheme


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
fun Main_DonationItem(
    imageRes: Int,
    title: String,
    money: String,
    progress: Float,
    progressText: String
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
            Article_Component(
                navController = navController,
                imageRes = R.drawable.banner,
                title = "폐지와 동전을 줍는 6살 서연이의 따뜻한 겨울나기를 도와주세요",
                description = "  6살 서연이는 어린 나이에도 불구하고 매일 폐지와 동전을 줍기 위해 거리를 나섭니다. 그의 부모님은 경제적인 어려움으로 인해 일을 구하기 힘들어 하시고, 서연이는 가족을 돕기 위해 이른 새벽부터 늦은 밤까지 길거리에서 작은 손으로 폐지와 동전을 모으고 있습니다.\n" +
                        "\n" +
                        "  서연이는 매서운 겨울바람을 맞으며 조금이라도 더 모으기 위해 노력합니다. 차가운 길거리를 걸을 때마다 서연이의 작은 손과 얼굴은 빨갛게 얼어붙습니다. 이 어린 소녀가 무거운 폐지 뭉치를 들고 다니는 모습은 주변 사람들의 마음을 아프게 합니다.\n" +
                        "\n" +
                        "  서연이의 가족은 따뜻한 겨울을 보내기 위해 여러 도움의 손길을 필요로 합니다. 기본적인 생활비는 물론, 서연이의 건강을 위해 겨울옷과 음식도 절실히 필요합니다. 서연이가 추운 겨울을 따뜻하고 건강하게 보낼 수 있도록 우리 모두가 함께 도울 수 있습니다.\n" +
                        "\n" +
                        "  서연이의 이야기는 많은 사람들에게 큰 감동을 주고 있습니다. 작은 도움이 모여 큰 힘이 될 수 있습니다. 따뜻한 겨울을 보낼 수 있도록 서연이와 그의 가족에게 사랑과 지원을 보내주세요."
            )
            Article_Component(
                navController = navController,
                imageRes = R.drawable.card,
                title = "굶주린 아이들을 위해 후원 바랍니다",
                description = "  오늘날 전 세계에는 수백만 명의 아이들이 매일 굶주림과 싸우고 있습니다. 빈곤과 기아로 고통받는 이 아이들은 제대로 된 식사를 하지 못한 채 하루하루를 버티고 있습니다. 이 아이들의 배고픔을 해결하기 위해 여러분의 도움이 절실합니다.\n" +
                        "\n" +
                        "  굶주린 아이들은 신체적, 정신적 발달에 심각한 영향을 받습니다. 영양 부족으로 인한 건강 문제와 함께, 배움의 기회마저 박탈당한 채 성장하고 있습니다. 이 아이들이 건강하고 밝은 미래를 꿈꿀 수 있도록 우리 모두가 힘을 모아야 할 때입니다.\n" +
                        "\n" +
                        "  세계 각지에서 진행되고 있는 구호 활동들은 아이들에게 필요한 식량과 의료 지원을 제공하고 있습니다. 그러나 여전히 많은 지역에서는 도움이 부족합니다. 특히, 전쟁이나 자연재해로 인해 고통받는 지역의 아이들은 더욱 심각한 상황에 놓여 있습니다.\n" +
                        "\n" +
                        "  여러분의 작은 후원은 큰 변화를 만들어낼 수 있습니다. 한 끼의 식사, 깨끗한 물, 그리고 교육의 기회가 이 아이들에게는 새로운 희망이 됩니다. 후원을 통해 아이들이 건강하게 자라고, 교육을 받으며, 밝은 미래를 꿈꿀 수 있도록 함께해 주세요."
            )
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
) {
    Column(
        modifier = Modifier
            .width(327.dp)
            .clickable {
                Log.d("클릭", "click")
                navController.navigate("article_screen/$imageRes/$title/$description")
            }
    ) {
        Image(
            painter = painterResource(imageRes), // 인자로 넘어온 이미지를 화면에 띄움
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