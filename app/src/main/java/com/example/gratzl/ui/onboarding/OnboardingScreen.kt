package com.example.gratzl.ui.onboarding

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gratzl.shared.theme.Green20
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Orange80
import com.example.gratzl.shared.theme.Teal20
import com.example.gratzl.shared.theme.Teal80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(
    onComplete: (selectedBezirk: String) -> Unit,
    onSkip: () -> Unit,
    initialBezirk: String = "1070 · Neubau"
) {
    var currentPage by remember { mutableStateOf(0) }
    var selectedBezirk by remember { mutableStateOf(initialBezirk) }
    val totalPages = 5

    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (currentPage > 0) {
                    IconButton(onClick = { currentPage-- }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Zurück")
                    }
                } else {
                    Spacer(Modifier.size(48.dp))
                }
                TextButton(onClick = onSkip) {
                    Text(
                        "Überspringen",
                        color = Teal80,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Crossfade(targetState = currentPage, label = "onboarding_page") { page ->
                    when (page) {
                        0 -> HomeBezirkPage(selectedBezirk, onBezirkChange = { selectedBezirk = it })
                        1 -> SearchPage()
                        2 -> AddNewPage()
                        3 -> ChatPage()
                        else -> ProfilePage()
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    repeat(totalPages) { index ->
                        val dotWidth by animateDpAsState(
                            targetValue = if (index == currentPage) 24.dp else 8.dp,
                            label = "dot_width_$index"
                        )
                        Box(
                            modifier = Modifier
                                .height(8.dp)
                                .width(dotWidth)
                                .clip(CircleShape)
                                .background(
                                    if (index == currentPage) Green80
                                    else Green80.copy(alpha = 0.25f)
                                )
                        )
                    }
                }
                Button(
                    onClick = {
                        if (currentPage < totalPages - 1) currentPage++
                        else onComplete(selectedBezirk)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Alles klar!")
                }
            }
        }
    }
}

@Composable
private fun PageLayout(
    title: String,
    description: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Text(
            description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(20.dp))
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBezirkPage(selectedBezirk: String, onBezirkChange: (String) -> Unit) {
    val districts = listOf(
        "1010 · Innere Stadt", "1020 · Leopoldstadt", "1030 · Landstraße",
        "1040 · Wieden", "1050 · Margareten", "1060 · Mariahilf",
        "1070 · Neubau", "1080 · Josefstadt", "1090 · Alsergrund",
        "1100 · Favoriten", "1110 · Simmering", "1120 · Meidling",
        "1130 · Hietzing", "1140 · Penzing", "1150 · Rudolfsheim",
        "1160 · Ottakring", "1170 · Hernals", "1180 · Währing",
        "1190 · Döbling", "1200 · Brigittenau", "1210 · Floridsdorf",
        "1220 · Donaustadt", "1230 · Liesing"
    )
    var expanded by remember { mutableStateOf(false) }

    PageLayout(
        title = "Dein Heimviertel",
        description = "Gib uns kurz deinen Bezirk – wir zeigen dir dann die Anzeigen aus deiner Gegend."
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Green80,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Mein Bezirk", style = MaterialTheme.typography.titleSmall)
                }
                Spacer(Modifier.height(12.dp))
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it }
                ) {
                    OutlinedTextField(
                        value = selectedBezirk,
                        onValueChange = {},
                        readOnly = true,
                        shape = RoundedCornerShape(12.dp),
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        districts.forEach { district ->
                            DropdownMenuItem(
                                text = { Text(district) },
                                onClick = {
                                    onBezirkChange(district)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchPage() {
    PageLayout(
        title = "Suche in der Nähe",
        description = "Hier findest du Angebote und Anfragen aus deinem Gratzl – von Gartenhilfe bis Nachhilfe."
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "Suche nach Angeboten...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Alle" to true, "Garten" to false, "Handwerk" to false).forEach { (label, active) ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(if (active) Green20 else MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                label,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (active) Green80 else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                listOf(true, false).forEach { isOffer ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(if (isOffer) Green20 else Teal20)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (isOffer) Green80 else Teal80)
                        )
                        Spacer(Modifier.width(12.dp))
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Box(
                                Modifier
                                    .width(120.dp)
                                    .height(8.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f))
                            )
                            Box(
                                Modifier
                                    .width(80.dp)
                                    .height(6.dp)
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.07f))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddNewPage() {
    PageLayout(
        title = "Teile dein Angebot",
        description = "Stell Angebote ein oder schreib Anfragen – für Handwerk, Garten, Bildung und mehr."
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(Green80)
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Ich biete", color = Color.White, style = MaterialTheme.typography.titleSmall)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Ich suche",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(horizontal = 12.dp, vertical = 14.dp)
                ) {
                    Text(
                        "Titel deiner Anzeige...",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    listOf("Bildung", "Garten", "+ mehr").forEach { label ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                label,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Green80)
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Anzeige aufgeben", color = Color.White, style = MaterialTheme.typography.titleSmall)
                }
            }
        }
    }
}

@Composable
private fun ChatPage() {
    val dummyChats = listOf(
        Triple("Maria W.", "Klingt super, wann passt es dir?", "14:32"),
        Triple("Jonas B.", "Danke für die schnelle Antwort!", "Gestern"),
        Triple("Sophie K.", "Wäre das möglich ab nächster Woche?", "Mo")
    )

    PageLayout(
        title = "Bleib in Kontakt",
        description = "Deine laufenden Chats mit Nachbarn – direkt und persönlich."
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                dummyChats.forEachIndexed { idx, (name, preview, time) ->
                    if (idx > 0) HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Green20),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                name.first().toString(),
                                color = Green80,
                                style = MaterialTheme.typography.titleSmall
                            )
                        }
                        Spacer(Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(name, style = MaterialTheme.typography.titleSmall)
                            Text(
                                preview,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1
                            )
                        }
                        Text(
                            time,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProfilePage() {
    PageLayout(
        title = "Dein Profil",
        description = "Passe dein Profil an, verwalte deine Inserate und zeig was du kannst."
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Green20),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Filled.Person,
                        contentDescription = null,
                        tint = Green80,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Text("Dein Name", style = MaterialTheme.typography.titleMedium)
                Row {
                    repeat(5) {
                        Icon(
                            Icons.Filled.Star,
                            contentDescription = null,
                            tint = Orange80,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)
                listOf("Persönliche Daten", "Meine Inserate", "Verfügbarkeit").forEach { item ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(item, style = MaterialTheme.typography.bodyMedium)
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
