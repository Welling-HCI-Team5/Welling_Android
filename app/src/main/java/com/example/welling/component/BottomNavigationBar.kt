package com.example.welling.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.welling.R

import com.example.welling.ui.theme.WellingTheme


@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,
        contentColor = Color.Gray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavigationItem(

            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "main_donation") Color(0xFFA4D41C) else Color.Gray
                )
            },
            selected = currentRoute == "main_donation",
            selectedContentColor = Color(0xFFA4D41C),
            unselectedContentColor = Color.Gray,
            onClick = {
                if (currentRoute != "main_donation") {
                    navController.navigate("main_donation") {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
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
                    painter = painterResource(R.drawable.icon__donate),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "ability_donation") Color(0xFFA4D41C) else Color.Gray
                )
            },
            selected = currentRoute == "ability_donation",
            selectedContentColor = Color(0xFFA4D41C),
            unselectedContentColor = Color.Gray,
            onClick = {
                if (currentRoute != "ability_donation") {
                    navController.navigate("ability_donation") {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
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
                    painter = painterResource(R.drawable.icon_bell),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "notifications") Color(0xFFA4D41C) else Color.Gray
                )
            },
            selected = currentRoute == "notifications",
            selectedContentColor = Color(0xFFA4D41C),
            unselectedContentColor = Color.Gray,
            onClick = {
                if (currentRoute != "notifications") {
                    navController.navigate("notifications") {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
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
                    painter = painterResource(R.drawable.icon_user),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = if (currentRoute == "my_page") Color(0xFFA4D41C) else Color.Gray
                )
            },
            selected = currentRoute == "my_page",
            selectedContentColor = Color(0xFFA4D41C),
            unselectedContentColor = Color.Gray,
            onClick = {
                if (currentRoute != "my_page") {
                    navController.navigate("my_page") {
//                        popUpTo(navController.graph.startDestinationId) {
//                            saveState = true
//                        }
                        //헉 코드 이렇게 짜면 안돼요
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
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