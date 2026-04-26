package com.example.gratzl.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.Listing

// Platzhalter — Levi baut die echte Version
@Composable
fun ListingCard(
    listing: Listing,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.width(180.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(listing.title, style = MaterialTheme.typography.titleSmall, maxLines = 1)
            Text(listing.district, style = MaterialTheme.typography.bodySmall)
        }
    }
}