package com.example.gratzl.shared.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

val navItems = listOf(
    NavItem(Routes.HOME,    "Home",   Icons.Filled.Home),
    NavItem(Routes.SEARCH,  "Suche",  Icons.Filled.Search),
    NavItem(Routes.ADD_NEW, "Neu",    Icons.Filled.Add),
    NavItem(Routes.CHAT,    "Chat",   Icons.Filled.Chat),
    NavItem(Routes.PROFILE, "Profil", Icons.Filled.Person),
)

@Composable
fun BottomNavBar(navController: NavController) {
    val currentRoute by navController.currentBackStackEntryAsState()
    val activeRoute = currentRoute?.destination?.route

    NavigationBar {
        navItems.forEach { item ->
            NavigationBarItem(
                selected = activeRoute == item.route,
                onClick  = {
                    if (activeRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(Routes.HOME) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon  = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}