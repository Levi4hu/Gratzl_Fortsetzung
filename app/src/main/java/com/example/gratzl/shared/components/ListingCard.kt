package com.example.gratzl.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Nunito
import com.example.gratzl.shared.theme.Teal80

val categoryEmoji = mapOf(
    "Garten"   to "🌿",
    "Bildung"  to "📚",
    "Haushalt" to "🏠",
    "Handwerk" to "🔧",
)

@Composable
fun ListingCard(
    listing: Listing,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentColor = if (listing.isOffer) Green80 else Teal80
    val bgColor = accentColor.copy(alpha = 0.12f)
    val emoji = categoryEmoji[listing.category] ?: "✨"

    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = accentColor.copy(alpha = 0.4f)
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(bgColor)
            ) {
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(accentColor)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        listing.category,
                        color = Color.White,
                        fontSize = 11.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    emoji,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(10.dp)
                )
            }

            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    listing.title,
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 1.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = accentColor,
                        modifier = Modifier.size(11.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        listing.district,
                        fontFamily = Nunito,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = accentColor
                    )
                }
                Text(
                    text = when (listing.priceType) {
                        PriceType.FREE     -> "Kostenlos"
                        PriceType.TRADE    -> "Tausch"
                        PriceType.COFFEE   -> "Kaffee ☕"
                        PriceType.PER_HOUR -> "${listing.price?.toInt() ?: 0} € / Std"
                        PriceType.FIXED    -> "${listing.price?.toInt() ?: 0} €"
                    },
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = accentColor,
                    modifier = Modifier.padding(top = 1.dp)
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