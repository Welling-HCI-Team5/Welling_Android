package com.example.welling.screen

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.welling.R
import com.example.welling.component.BtnMainColor
import com.example.welling.component.CustomTextBox
import com.example.welling.component.TopAppBar
import com.example.welling.ui.theme.WellingTheme


@Composable
fun DonationDetailScreen(
    navController: NavHostController,
    imageRes: String, // Int
    title: String,
    description: String
) {
    val imageResToInt = imageRes.toInt()

    val context = LocalContext.current
    val scrollState = rememberScrollState() // 스크롤 상태를 기억

    var currentDonatorNum by remember { mutableStateOf(0) } // 추가로 보이는 최근 기부자 수
    var currentProgress by remember { mutableStateOf(0f) } // progress bar 현재 상태값

    currentDonatorNum = 442
    currentProgress = 0.1F

    var donationName = title
    //val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E)  HH:mm")
    var donationDate = "2024.05.30 (목) 13:24"//formatter.format(LocalDate.now())
    //"2024.05.30 (목) 13:24"
    var donationImgId = imageResToInt
    var donationContent = description

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // 상단바
            TopAppBar(
                onBackIconClick = { navController.popBackStack() },
                title = "기부",
                onRightIconClick = null,
                rightIconImgId = null,
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
            ) {
                // 사진
                Image(
                    painter = painterResource(id = donationImgId),
                    contentDescription = "기부 상세 화면 이미지",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.height(24.dp))

                // 최근 기부자들
                CustomTextBox( // 제목
                    text = "최근 기부자들",
                    colorId = R.color.semi_black,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 8.dp
                )
                OverlappingImagesWithText(
                    imageIds = listOf(
                        R.drawable.img_size38,
                        R.drawable.img_size38,
                        R.drawable.img_size38,
                        R.drawable.img_size38,
                        R.drawable.img_size38
                    ), text = "${currentDonatorNum}"
                )
                Spacer(modifier = Modifier.height(24.dp))

                // 기부 현황 상태바
                CustomProgressBox(currentProgress = currentProgress, spacerHeight = 24.dp)

                // 기부 상세 정보 (제목, 내용)
                CustomTextBox( // 제목
                    text = donationName,
                    colorId = R.color.semi_black,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 24.dp
                )
                CustomTextBox( // 내용
                    text = donationContent,
                    colorId = R.color.semi_black,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    textOnClick = null,
                    spacerHeight = 5.dp
                )
                CustomTextBox( // 더보기 버튼
                    text = "더보기",
                    colorId = R.color.main_color,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                    textOnClick = {
                        Toast.makeText(context, "더보기 버튼 클릭됨!", Toast.LENGTH_SHORT).show()
                    },
                    spacerHeight = 24.dp
                )

                // 비슷한 프로그램
                CustomTextBox( // 더보기 버튼
                    text = "비슷한 프로그램",
                    colorId = R.color.semi_black,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 16.dp
                )
//                Spacer(modifier = Modifier.height(200.dp))
                SimilarPrograms(navController)

                // 기자 정보
                AuthorInfoBox(
                    authorName = "Any Mila",
                    authorInfo = "뉴욕 현지 여행 기자, BBC USA 소속 기자",
                    authorImgId = R.drawable.img_author
                )

                Spacer(modifier = Modifier.height(70.dp))

                // '기부하기' btn
                BtnMainColor(
                    text = "기부하기",
                    onClick = {
                        Toast.makeText(context, "기부하기 버튼 클릭됨!", Toast.LENGTH_SHORT).show()
                        navController.navigate("donation_progress")
                    }
                )
            }
        }
    }
}

@Composable
fun OverlappingImagesWithText(
    imageIds: List<Int>,
    text: String
) {
    val strokeColor = colorResource(id = R.color.gray_6c7072)

    Row(verticalAlignment = Alignment.CenterVertically) {
        imageIds.forEachIndexed { index, imageResId ->
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .offset(x = (-10 * index).dp)  // 이미지들이 겹치도록 오프셋 조정
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = "Overlapping Image $index",
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)  // 이미지를 원형으로 클리핑
                )
                // 테두리 추가
                Canvas(modifier = Modifier.size(38.dp)) {
                    drawCircle(
                        color = strokeColor,
                        radius = size.minDimension / 2,
                        style = Stroke(2f.dp.toPx())
                    )
                }
            }
        }
        // 텍스트
        Text(
            text = "+${text}",
            color = colorResource(id = R.color.purple_5e5873),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.pretendard_medium)),
            modifier = Modifier.offset(x = (-32).dp)  // 이미지들에서 약간 오른쪽으로 위치 조정
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun CustomProgressBox(
    currentProgress: Float,
    spacerHeight: Dp
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CustomTextBox(
                text = "$ 190,00",
                colorId = R.color.semi_black,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                textOnClick = null,
                spacerHeight = 2.dp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "시계 아이콘",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            CustomTextBox(
                text = "15일 남았어요",
                colorId = R.color.main_color,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_medium)),
                textOnClick = null,
                spacerHeight = 2.dp,
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            LinearProgressIndicator(
                progress = currentProgress,
                modifier = Modifier
                    .weight(5f)
                    .height(10.dp)
                    .clip(RoundedCornerShape(10.dp)),
                color = colorResource(id = R.color.main_color),
                trackColor = colorResource(id = R.color.gray_e3e5e5),
            )
            Spacer(modifier = Modifier.width(6.dp))
            CustomTextBox(
                text = "${(currentProgress * 100).toInt()}%",
                colorId = R.color.gray_6c7072,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                textOnClick = null,
                spacerHeight = 2.dp,
            )
        }
        Spacer(modifier = Modifier.height(spacerHeight))
    }
}

// 비슷한 프로그램 dummy data로 구현
@Composable
fun SimilarPrograms(navController: NavController) {
    val similiarItems = listOf(
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

    Column {
        similiarItems.forEach { item ->
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
fun AuthorInfoBox(
    authorName: String,
    authorInfo: String,
    authorImgId: Int
) {
    Row {
        Column {
            CustomTextBox(
                text = "By ${authorName}",
                colorId = R.color.semi_black,
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                textOnClick = null,
                spacerHeight = 8.dp,
            )
            CustomTextBox(
                text = authorInfo,
                colorId = R.color.semi_black,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.pretendard_regular)),
                textOnClick = null,
                spacerHeight = 0.dp,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(id = authorImgId),
            contentDescription = "기부 상세 화면 작가 이미지",
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }
}

@Preview(showBackground = true, device = "spec:width=375dp,height=812dp")
@Composable
fun Detail_DefaultPreview() {
    WellingTheme {
//        DonationDetailScreen(rememberNavController())
    }
}