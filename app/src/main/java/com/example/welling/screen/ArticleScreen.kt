package com.example.welling.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.component.BtnMainColor
import com.example.welling.component.CustomTextBox
import com.example.welling.component.TopAppBar
import com.example.welling.ui.theme.WellingTheme

@Composable
fun ArticleScreen(navController: NavHostController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState() // 스크롤 상태를 기억

    var articleName = "폐지와 동전을 줍는 6살 서연이의\n따뜻한 겨울나기를 도와주세요"
    var articleDate = "2024.05.30 (목) 13:24"
    var articleImgId = R.drawable.img_article
    var articleContent = "이 기부는 공정무역 청소년을 위한 기부입니다.\n" +
            "아직 수 많은 아프리카 지역의 아이들은 아동노동법에 위반하여, 제대로된 임금을 받지 못하고 초콜릿 공장에서 착취를 당하고 있습니다.\n" +
            "\n" +
            "아직 수 많은 아프리카 지역의 아이들은 아동노동법에 위반하여, 제대로된 임금을 받지 못하고 초콜릿 공장에서 착취를 당하고 있습니다.\n"

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // 상단바
            TopAppBar(
                onBackIconClick = { navController.popBackStack() },
                title = "기사",
                onRightIconClick = null,
                rightIconImgId = null,
            )

            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxHeight()
                    .verticalScroll(scrollState)
            ) {
                // 제목
                CustomTextBox(
                    text = articleName,
                    colorId = R.color.semi_black,
                    fontSize = 22.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 12.dp
                )

                // 일시
                CustomTextBox(
                    text = articleDate,
                    colorId = R.color.main_color_deep,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 12.dp
                )

                // 사진
                Image(
                    painter = painterResource(id = articleImgId),
                    contentDescription = "기부 상세 화면 이미지",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 내용
                CustomTextBox(
                    text = articleContent,
                    colorId = R.color.semi_black,
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.pretendard_semibold)),
                    textOnClick = null,
                    spacerHeight = 12.dp
                )

                Spacer(modifier = Modifier.weight(1f))

                // '기부하기' btn
                BtnMainColor(
                    text = "관련 기부 진행하기",
                    onClick = {
                        Toast.makeText(context, "관련 기부 진행하기 버튼 클릭됨!", Toast.LENGTH_SHORT).show()
                        navController.navigate("donation_detail_screen")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=375dp,height=812dp")
@Composable
fun Article_DefaultPreview() {
    WellingTheme {
        ArticleScreen(rememberNavController())
    }
}