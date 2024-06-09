package com.example.welling.component

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.welling.MainViewModel
import com.example.welling.screen.Ability_Donation_Screen
import com.example.welling.screen.ArticleScreen
import com.example.welling.screen.DonationDetailScreen
import com.example.welling.screen.DonationProgressScreen
import com.example.welling.screen.Main_Donation_Screen
import com.example.welling.screen.MyPageScreen
import com.example.welling.screen.Notice_Screen
import com.example.welling.screen.SplashScreen
import com.example.welling.screen.StartSettingScreen


@Composable
fun NavigationComponent(navController: NavHostController) {
    val mainViewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController, mainViewModel)
        }
        composable("start_setting_screen") { StartSettingScreen(navController, mainViewModel) }
        composable("main_donation") { Main_Donation_Screen(navController, mainViewModel) }
        composable("ability_donation") {
            Ability_Donation_Screen(navController, mainViewModel)
        }
        // 후원 화면 -> 기사 화면
        composable("article_screen" + "/{imageRes}/{title}/{description}") {
            val imageRes = it.arguments?.getString("imageRes")!!
            val title = it.arguments?.getString("title")!!
            val description = it.arguments?.getString("description")!!
            ArticleScreen(navController, imageRes, title, description)
        }
        // 재능 기부 화면 -> 기부 상세 화면
        composable("donation_detail_screen" + "/{imageRes}/{title}/{description}") {
            val imageRes = it.arguments?.getString("imageRes")!!
            val title = it.arguments?.getString("title")!!
            val description = it.arguments?.getString("description")!!
            DonationDetailScreen(navController, imageRes, title, description)
        }
        composable("notifications") { Notice_Screen(navController) }
        composable("my_page") { MyPageScreen(navController) }
        composable("donation_progress") { DonationProgressScreen(navController) }
    }
}