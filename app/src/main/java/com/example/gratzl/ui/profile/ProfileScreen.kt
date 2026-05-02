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

@Composable
fun ProfileScreen(
    onNavigateToSettings: () -> Unit
) {
    val user = SampleData.getUserById(1)

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
                onBackClick = {}
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
                onEditClick = {}
            )

            ProfileStatsRow(user)

            OwnProfileMenuCard(
                onPersonalDataClick = {},
                onListingsClick = {},
                onSupportClick = {}
            )

            AvailabilityCard(
                user = user,
                showEdit = true,
                onEditClick = {}
            )

            SkillsCard(
                skills = user.skills,
                isOwnProfile = true,
                detailed = true,
                onEditClick = {}
            )

            OutlinedButton(
                onClick = {},
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