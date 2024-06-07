package com.example.welling.component

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.welling.MainViewModel
import com.example.welling.screen.MyPageScreen
import com.example.welling.screen.Ability_Donation_Screen
import com.example.welling.screen.ArticleScreen
import com.example.welling.screen.DonationDetailScreen
import com.example.welling.screen.DonationProgressScreen
import com.example.welling.screen.Main_Donation_Screen
import com.example.welling.screen.Notice_Screen
import com.example.welling.screen.SplashScreen
import com.example.welling.screen.StartSettingScreen


@Composable
fun NavigationComponent() {
    val navController = rememberNavController()
    val mainViewModel: MainViewModel = viewModel()

    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") { SplashScreen(navController, mainViewModel) }
        composable("start_setting_screen") { StartSettingScreen(navController, mainViewModel) }
        composable("main_donation") { Main_Donation_Screen(navController, mainViewModel) }
        composable("ability_donation") { Ability_Donation_Screen(navController, mainViewModel) }
        composable("article_screen") { ArticleScreen(navController) }
        composable("donation_detail_screen") { DonationDetailScreen(navController) }
        composable("notifications") { Notice_Screen(navController) }
        composable("my_page") { MyPageScreen(navController) }
        composable("donation_progress") { DonationProgressScreen(navController)}
    }
}