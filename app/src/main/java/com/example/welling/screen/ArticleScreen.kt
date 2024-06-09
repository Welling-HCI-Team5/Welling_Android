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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.component.BtnMainColor
import com.example.welling.component.CustomTextBox
import com.example.welling.component.TopAppBar
import com.example.welling.ui.theme.WellingTheme
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ArticleScreen(
    navController: NavController,
    imageRes: String,//Int
    title: String,
    description: String
)
{
    val imageResToInt = imageRes.toInt()

    val context = LocalContext.current
    val scrollState = rememberScrollState() // 스크롤 상태를 기억

    var articleName = title
    //val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd (E)  HH:mm")
    var articleDate = "2024.05.30 (목) 13:24"//formatter.format(LocalDate.now())
       //"2024.05.30 (목) 13:24"
    var articleImgId = imageResToInt
    var articleContent = description
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
        //ArticleScreen(rememberNavController())
    }
}