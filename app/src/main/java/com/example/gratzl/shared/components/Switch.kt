package com.example.gratzl.shared.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Teal80

@Composable
fun Switch(
    isOfferMode: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick = { onToggle(true) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(isOfferMode)
                    Green80
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) { Text("Angebote") }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick = { onToggle(false) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = if(!isOfferMode)
                    Teal80
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) { Text("Anfragen") }
    }
}

/*
Benutzung:
Switch(
   isOfferMode = state.isOfferMode,
   onToggle = { viewModel.toggleMode(it) }
)
 */