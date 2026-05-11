package com.example.gratzl.shared.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String = "Gratzl",
    showBack: Boolean = false,
    onBackClick: () -> Unit = {},
    actionIcon: ImageVector? = null,
    onActionClick: () -> Unit = {},
    showFavorite: Boolean = false,
    showFlagReport: Boolean = false,
    showSettings: Boolean = false,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onFlagReportClick: () -> Unit = {}
    //showReport:
) {
    TopAppBar(
        title = {
            Text(
                title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )
        },
        navigationIcon = {
            if(showBack) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Zurück")
                }
            }
        },
        actions = {
            if(showFavorite){
                IconButton(onClick = onFavoriteClick){
                    Icon(
                        imageVector = if(isFavorite)
                            Icons.Filled.Favorite
                        else
                        Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorit",
                        tint= if(isFavorite) Color.Red
                        else MaterialTheme.colorScheme.onSurface
                    )
                }
            }
            if (showFlagReport) {
                IconButton(onClick = onFlagReportClick) {
                    Icon(
                        imageVector = Icons.Default.Flag,
                        contentDescription = "Profil melden"
                    )
                }
            }

            if (showSettings) {
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Einstellungen",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            if(actionIcon != null) {
                IconButton(onClick = onActionClick) {
                    Icon(actionIcon, contentDescription = null)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}


/*
TODO: TOPBAR Aufruf bei jeweiligen Screens
Für Home, Search, Chat:
AppTopBar(title = "Gratzl")

Pfeil Zurück
AppTopBar(
  title = "Anzeige",
    showBack = true,
    onBackClick = { onNavigationBack() }

Report Icon für Chat
AppTopBar(
  title = "Mara L.",
  showBack = true,
  onBackClick = { onNavigationBack() },
  actionIcon = Icons.Filled.Flag,
  onActionClick = { REPORT }
 */