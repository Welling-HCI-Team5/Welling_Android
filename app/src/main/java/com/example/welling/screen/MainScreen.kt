package com.example.welling.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.welling.component.BottomNavigationBar
import com.example.welling.component.NavigationComponent

//import com.example.welling.screen.MyPageScreen

@Composable
fun MainScreen(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarVisible = when (currentRoute) {
        "main_donation", "ability_donation", "notifications", "my_page" -> true
        else -> false
    }

    val topBarHide = currentRoute == "splash_screen"

    Scaffold(
        topBar = {
            if (topBarHide) {
                // 여기서는 상단바를 숨기는 부분만 작성해 둠 (상단바 필요한 화면에서 각각 TopAppBar 호출하면 됨)
                Box(modifier = Modifier) {} // 빈 Box로 대체하여 상단바 공간을 확보하지 않음
            }
        },
        bottomBar = {
            if (bottomBarVisible) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        Box(Modifier.padding(it)) {
            NavigationComponent(navController = navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MMainScreen() {
    MainScreen(rememberNavController())
}
