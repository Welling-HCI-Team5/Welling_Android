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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.welling.R

@Composable
fun BottomNavigationBar(navController: NavHostController) {
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
                    painter = painterResource(if (currentRoute == "profile") R.drawable.coloruser else R.drawable.user),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            },
            selected = currentRoute == "profile",
            onClick = {
                if (currentRoute != "profile") {
                    navController.navigate("profile") {
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