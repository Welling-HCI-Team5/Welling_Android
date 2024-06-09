package com.example.welling.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    Scaffold(bottomBar = {
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
