package com.example.welling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.welling.ui.theme.WellingTheme
import com.example.welling.Screen.*

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WellingTheme {
                WellingApp(mainViewModel)
            }
        }
    }
}

@Composable
fun WellingApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isFirstLaunch by mainViewModel.isFirstLaunch.collectAsState()

    Scaffold(
        bottomBar = {
            if (currentRoute != "start_setting" && currentRoute != "donation_progress") {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isFirstLaunch) "start_setting" else "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("start_setting") { StartSettingScreen(navController, mainViewModel) }
            composable("main") { MainScreen(navController) }
            composable("donation_progress") { DonationProgressScreen(navController) }
            composable("talent_donation") { TalentDonationScreen() }
            composable("notifications") { NotificationsScreen() }
            composable("my_page") { MyPageScreen() }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Home") },
            selected = currentRoute == "main",
            onClick = { navController.navigate("main") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_talent_donate), contentDescription = "Talent Donation") },
            selected = currentRoute == "talent_donation",
            onClick = { navController.navigate("talent_donation") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_notifications), contentDescription = "Notifications") },
            selected = currentRoute == "notifications",
            onClick = { navController.navigate("notifications") }
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_profile), contentDescription = "My Page") },
            selected = currentRoute == "my_page",
            onClick = { navController.navigate("my_page") }
        )
    }
}
