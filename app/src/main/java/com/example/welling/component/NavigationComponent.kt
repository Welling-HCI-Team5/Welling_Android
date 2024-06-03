package com.example.welling.component

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.welling.screen.Ability_Donation_Screen
import com.example.welling.screen.Main_Donation_Screen
import com.example.welling.screen.ArticleScreen
import com.example.welling.screen.DonationDetailScreen
import com.example.welling.screen.Notice_Screen


@Composable
fun NavigationComponent() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_donation") {
        composable("main_donation") { Main_Donation_Screen(navController) }
        composable("ability_donation") { Ability_Donation_Screen(navController) }
        composable("article_screen") { ArticleScreen(navController) }
        composable("donation_detail_screen") { DonationDetailScreen(navController) }
        composable("notifications") { Notice_Screen(navController) }
    }


}