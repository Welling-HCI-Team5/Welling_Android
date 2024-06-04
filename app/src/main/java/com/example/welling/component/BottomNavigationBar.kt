package com.example.welling.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.welling.R
import com.example.welling.screen.Main_Donation_Screen
import com.example.welling.ui.theme.WellingTheme
import com.example.welling.component.NavigationComponent

@Composable
fun BottomNavigationBar(navController: NavHostController,) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(if (currentRoute == "main_donation") R.drawable.colorhome else R.drawable.grayhome),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = currentRoute == "main_donation",
            onClick = {
                if (currentRoute != "main_donation") {
                    navController.navigate("main_donation") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(if (currentRoute == "ability_donation") R.drawable.colorway else R.drawable.grayway),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = currentRoute == "ability_donation",
            onClick = {
                if (currentRoute != "ability_donation") {
                    navController.navigate("ability_donation") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(if (currentRoute == "notifications") R.drawable.colorbell else R.drawable.bell),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = currentRoute == "notifications",
            onClick = {
                if (currentRoute != "notifications") {
                    navController.navigate("notifications") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    painter = painterResource(if (currentRoute == "my_page") R.drawable.coloruser else R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = currentRoute == "my_page",
            onClick = {
                if (currentRoute != "my_page") {
                    navController.navigate("my_page") {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        )
    }
}


@Preview(showBackground = true, device = "spec:width=375dp,height=812dp")
@Composable
fun Navi_DefaultPreview() {
    WellingTheme {
        BottomNavigationBar(rememberNavController())
    }
}