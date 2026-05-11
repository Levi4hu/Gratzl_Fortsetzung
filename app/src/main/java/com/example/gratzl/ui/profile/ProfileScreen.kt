package com.example.gratzl.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.shared.components.AppTopBar
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext


@Composable
fun ProfileScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToSavedListings: () -> Unit
) {
    val user = SampleData.getUserById(1)
    val context = LocalContext.current
    val showWorkInProgress = {
        Toast.makeText(context, "Work in progress", Toast.LENGTH_SHORT).show()
    }
    val savedListingCount = SampleData.savedListings.size


    if (user == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Profil konnte nicht geladen werden.")
        }
        return
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Mein Profil",
                showSettings = true,
                onBackClick = showWorkInProgress
            ) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ProfileHeaderCard(
                user = user,
                isOwnProfile = true,
                onEditClick = showWorkInProgress
            )

            ProfileStatsRow(
                user = user,
                savedListingCount = savedListingCount,
                onSavedListingsClick = onNavigateToSavedListings
            )

            OwnProfileMenuCard(
                onPersonalDataClick = showWorkInProgress,
                onListingsClick = showWorkInProgress,
                onSupportClick = showWorkInProgress
            )

            AvailabilityCard(
                user = user,
                showEdit = true,
                onEditClick = showWorkInProgress
            )

            SkillsCard(
                skills = user.skills,
                isOwnProfile = true,
                detailed = true,
                onEditClick = showWorkInProgress
            )

            OutlinedButton(
                onClick = showWorkInProgress,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text("Abmelden")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}