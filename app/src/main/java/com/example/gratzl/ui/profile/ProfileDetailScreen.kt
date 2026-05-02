package com.example.gratzl.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.shared.components.AppTopBar

@Composable
fun ProfileDetailScreen(
    userId: Int,
    onNavigateBack: () -> Unit,
    onNavigateToListing: (Int) -> Unit,
    onNavigateToChat: (Int) -> Unit
) {
    val user = SampleData.getUserById(userId)
    val listings = SampleData.listings.filter { it.userId == userId }

    if (user == null) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Profil konnte nicht gefunden werden.")
        }
        return
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title       = "Neue Anzeige",
                showBack    = true,
                showFlagReport = true,
                onBackClick = onNavigateBack
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    val existingChat = SampleData.chats.firstOrNull { it.partnerId == userId }
                    if (existingChat != null) {
                        onNavigateToChat(existingChat.id)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .height(56.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Chat,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text("Nachricht schreiben")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            ProfileHeaderCard(
                user = user,
                isOwnProfile = false
            )

            ProfileStatsRow(user)

            AvailabilityCard(
                user = user,
                showEdit = false
            )

            SkillsCard(
                skills = user.skills,
                isOwnProfile = false,
                detailed = false
            )

            ActiveListingsCard(
                listings = listings,
                onNavigateToListing = onNavigateToListing
            )

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}