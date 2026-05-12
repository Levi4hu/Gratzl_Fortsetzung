package com.example.gratzl.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratzl.shared.theme.Nunito

@Composable
fun AboutScreen(
    onShowApp: () -> Unit,
    onLater: () -> Unit
) {
    Scaffold(containerColor = MaterialTheme.colorScheme.background) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(56.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF2A6B45))
                    ) {
                        Text(
                            "G",
                            fontFamily = Nunito,
                            color = Color.White,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Text(
                        "Gratzl",
                        fontFamily = Nunito,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = (-1).sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                Spacer(Modifier.height(48.dp))
                Text(
                    "Willkommen im Gratzl",
                    style = MaterialTheme.typography.displaySmall,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    "Gratzl verbindet dich mit deiner Nachbarschaft in Wien. " +
                    "Biete Hilfe an, such dir Unterstützung – beim Handwerk, im Garten, " +
                    "bei der Bildung oder einfach im Alltag. " +
                    "Weil gute Nachbarschaft das Leben leichter macht.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onShowApp,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Zeig mir die App!")
                }
                OutlinedButton(
                    onClick = onLater,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Vielleicht später mal")
                }
                Spacer(Modifier.height(16.dp))
            }
        }
    }
}
