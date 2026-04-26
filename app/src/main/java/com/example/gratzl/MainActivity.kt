package com.example.gratzl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gratzl.shared.navigation.BottomNavBar
import com.example.gratzl.shared.navigation.Routes
import com.example.gratzl.shared.theme.MarktplatzTheme
import com.example.gratzl.ui.home.HomeScreen
import com.example.gratzl.ui.search.SearchScreen
import com.example.gratzl.ui.addnew.AddNewScreen
import com.example.gratzl.ui.chat.ChatListScreen
import com.example.gratzl.ui.chat.ChatScreen
import com.example.gratzl.ui.profile.ProfileScreen
import com.example.gratzl.ui.profile.ProfileDetailScreen
import com.example.gratzl.ui.listing.ListingDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isOfferMode by remember { mutableStateOf(true) }

            MarktplatzTheme(isOfferMode = isOfferMode) {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = { BottomNavBar(navController) }
                ) { innerPadding ->
                    NavHost(
                        navController    = navController,
                        startDestination = Routes.HOME,
                        modifier         = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.HOME) {
                            HomeScreen(
                                onNavigateToListing = { id ->
                                    navController.navigate(Routes.listingDetail(id))
                                }
                            )
                        }
                        composable(Routes.SEARCH) {
                            SearchScreen(
                                onNavigateToListing = { id ->
                                    navController.navigate(Routes.listingDetail(id))
                                }
                            )
                        }
                        composable(Routes.ADD_NEW) {
                            AddNewScreen(
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.CHAT) {
                            ChatListScreen(
                                onNavigateToChat = { id ->
                                    navController.navigate(Routes.chatDetail(id))
                                }
                            )
                        }
                        composable(Routes.PROFILE) {
                            ProfileScreen(
                                onNavigateToSettings = { }
                            )
                        }
                        composable(Routes.LISTING_DETAIL) { backStack ->
                            val id = backStack.arguments?.getString("listingId")?.toIntOrNull() ?: return@composable
                            ListingDetailScreen(
                                listingId        = id,
                                onNavigateToChat = { chatId ->
                                    navController.navigate(Routes.chatDetail(chatId))
                                },
                                onNavigateBack   = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.CHAT_DETAIL) { backStack ->
                            val id = backStack.arguments?.getString("chatId")?.toIntOrNull() ?: return@composable
                            ChatScreen(
                                chatId              = id,
                                onNavigateToProfile = { userId ->
                                    navController.navigate(Routes.profileDetail(userId))
                                },
                                onNavigateBack      = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.PROFILE_DETAIL) { backStack ->
                            val id = backStack.arguments?.getString("userId")?.toIntOrNull() ?: return@composable
                            ProfileDetailScreen(
                                userId         = id,
                                onNavigateBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}