package com.example.gratzl

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gratzl.shared.components.GraetzelLogo
import com.example.gratzl.shared.navigation.BottomNavBar
import com.example.gratzl.shared.navigation.Routes
import com.example.gratzl.shared.theme.MarktplatzTheme
import com.example.gratzl.ui.addnew.AddNewScreen
import com.example.gratzl.ui.chat.ChatListScreen
import com.example.gratzl.ui.chat.ChatScreen
import com.example.gratzl.ui.home.HomeScreen
import com.example.gratzl.ui.listing.ListingDetailScreen
import com.example.gratzl.ui.onboarding.AboutScreen
import com.example.gratzl.ui.onboarding.OnboardingScreen
import com.example.gratzl.ui.profile.ProfileDetailScreen
import com.example.gratzl.ui.profile.ProfileScreen
import com.example.gratzl.ui.profile.SavedListingsScreen
import com.example.gratzl.ui.search.SearchScreen

// Auf false setzen um beim Testen direkt in die App zu springen
private const val SHOW_ONBOARDING = true

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val prefs = getSharedPreferences("gratzl_prefs", Context.MODE_PRIVATE)
        val onboardingComplete = prefs.getBoolean("onboarding_complete", false)
        val startDest = if (SHOW_ONBOARDING && !onboardingComplete) Routes.ABOUT else Routes.HOME

        setContent {
            var isOfferMode by remember { mutableStateOf(true) }
            var selectedBezirk by remember { mutableStateOf("1070 · Neubau") }

            MarktplatzTheme(isOfferMode = isOfferMode) {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomBar = currentRoute !in listOf(Routes.ABOUT, Routes.ONBOARDING)

                Scaffold(
                    bottomBar = { if (showBottomBar) BottomNavBar(navController) },
                    contentWindowInsets = WindowInsets(0, 0, 0, 0)
                ) { innerPadding ->
                    NavHost(
                        navController    = navController,
                        startDestination = startDest,
                        modifier         = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.ABOUT) {
                            AboutScreen(
                                onShowApp = { navController.navigate(Routes.ONBOARDING) },
                                onLater = {
                                    navController.navigate(Routes.HOME) {
                                        popUpTo(Routes.ABOUT) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Routes.ONBOARDING) {
                            OnboardingScreen(
                                initialBezirk = selectedBezirk,
                                onComplete = { bezirk ->
                                    selectedBezirk = bezirk
                                    prefs.edit().putBoolean("onboarding_complete", true).apply()
                                    navController.navigate(Routes.HOME) {
                                        popUpTo(Routes.ABOUT) { inclusive = true }
                                    }
                                },
                                onSkip = {
                                    prefs.edit().putBoolean("onboarding_complete", true).apply()
                                    navController.navigate(Routes.HOME) {
                                        popUpTo(Routes.ABOUT) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Routes.HOME) {
                            HomeScreen(
                                selectedBezirk = selectedBezirk,
                                onBezirkChange = { selectedBezirk = it },
                                onNavigateToListing = { id ->
                                    navController.navigate(Routes.listingDetail(id))
                                }
                            )
                        }
                        composable(Routes.SEARCH) {
                            SearchScreen(
                                selectedBezirk = selectedBezirk,
                                onBezirkChange = { selectedBezirk = it },
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
                                onNavigateToSettings = { },
                                onNavigateToSavedListings = {
                                    navController.navigate(Routes.SAVED_LISTINGS)
                                }
                            )
                        }
                        composable(Routes.SAVED_LISTINGS) {
                            SavedListingsScreen(
                                onNavigateBack = {
                                    navController.popBackStack()
                                },
                                onNavigateToListing = { listingId ->
                                    navController.navigate(Routes.listingDetail(listingId))
                                }
                            )
                        }
                        composable(Routes.LISTING_DETAIL) { backStack ->
                            val id = backStack.arguments?.getString("listingId")?.toIntOrNull() ?: return@composable
                            ListingDetailScreen(
                                listingId = id,
                                onNavigateToChat = { chatId ->
                                    navController.navigate(Routes.chatDetail(chatId))
                                },
                                onNavigateToProfile = { userId ->
                                    navController.navigate(Routes.profileDetail(userId))
                                },
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
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
                                userId = id,
                                onNavigateBack = {
                                    navController.popBackStack()
                                },
                                onNavigateToListing = { listingId ->
                                    navController.navigate(Routes.listingDetail(listingId))
                                },
                                onNavigateToChat = { chatId ->
                                    navController.navigate(Routes.chatDetail(chatId))
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
