package com.example.gratzl.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Teal80

@Composable
fun ListingCard(
    listing: Listing,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        border = BorderStroke(
            width = 3.dp,
            color = if(listing.isOffer) Green80 else Teal80
        )
    ) {
        Column{

            //Bild Platzhalter
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(listing.category, style = MaterialTheme.typography.bodySmall, color = if(listing.isOffer) Green80 else Teal80)
            }
            Column(modifier = Modifier.padding(10.dp)) {
                Text(listing.title, style = MaterialTheme.typography.titleSmall, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Text(listing.district, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(
                    text = when(listing.priceType) {
                        PriceType.FREE -> "Kostenlos"
                        PriceType.TRADE -> "Tausch"
                        PriceType.COFFEE -> "Kaffee"
                        PriceType.PER_HOUR -> "${listing.price} € / Std"
                        PriceType.FIXED -> "${listing.price} €"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

/*
ListingCard(
    listing  = einListing,
    onClick  = { onNavigateToListing(listing.id) },
    modifier = Modifier.fillMaxWidth()
)

Für HomeScreen:
LazyRow {
     items(listings) { listing ->
     ListingCard(listing, onClick = { onNavigateToListing(listing.id) })
   }
}
 */