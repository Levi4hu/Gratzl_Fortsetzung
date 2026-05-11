package com.example.gratzl.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Nunito
import com.example.gratzl.shared.theme.Teal80

@Composable
fun Switch(
    isOfferMode: Boolean,
    onToggle: (Boolean) -> Unit,
    offerCount: Int = 0,
    requestCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = { onToggle(true) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isOfferMode) Green80 else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    "Angebote",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    color = if (isOfferMode) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (offerCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (isOfferMode) Color.White.copy(alpha = 0.25f) else Green80.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "$offerCount",
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 1.dp),
                            fontSize = 11.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color = if (isOfferMode) Color.White else Green80
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { onToggle(false) },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!isOfferMode) Teal80 else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    "Anfragen",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    color = if (!isOfferMode) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (requestCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (!isOfferMode) Color.White.copy(alpha = 0.25f) else Teal80.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "$requestCount",
                            modifier = Modifier.padding(horizontal = 7.dp, vertical = 1.dp),
                            fontSize = 11.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color = if (!isOfferMode) Color.White else Teal80
                        )
                    }
                }
            }
        }
    }
}

/*
Benutzung:
Switch(
   isOfferMode = state.isOfferMode,
   onToggle = { viewModel.toggleMode(it) }
)
 */