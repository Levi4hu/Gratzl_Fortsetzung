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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Teal80
import com.example.gratzl.ui.home.HomeFilter

@Composable
fun SwitchAll(
    filter: HomeFilter,
    onFilterChange: (HomeFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Button(
            onClick  = { onFilterChange(HomeFilter.ALL) },
            modifier = Modifier.weight(1f),
            colors   = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ALL)
                    Color.Gray
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) { Text("Alle") }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick  = { onFilterChange(HomeFilter.ANGEBOTE) },
            modifier = Modifier.weight(1f),
            colors   = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ANGEBOTE)
                    Green80
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) { Text("Angebote") }

        Spacer(modifier = Modifier.width(8.dp))

        Button(
            onClick  = { onFilterChange(HomeFilter.ANFRAGEN) },
            modifier = Modifier.weight(1f),
            colors   = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ANFRAGEN)
                    Teal80
                else
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) { Text("Anfragen") }
    }
}