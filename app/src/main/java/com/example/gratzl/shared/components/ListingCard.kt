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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Nunito
import com.example.gratzl.shared.theme.Teal80


fun getImageUrlForListing(listing: Listing): String {
    val title = listing.title.lowercase()
    return when {
        // Bildung - spezifisch
        "gitarre" in title || "guitar" in title   -> "https://images.unsplash.com/photo-1510915361894-db8b60106cb1?w=400&q=80"
        "klavier" in title || "piano" in title     -> "https://images.unsplash.com/photo-1520523839897-bd0b52f945a0?w=400&q=80"
        "yoga" in title                            -> "https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?w=400&q=80"
        "koch" in title || "backen" in title       -> "https://images.unsplash.com/photo-1556909114-f6e7ad7d3136?w=400&q=80"
        "nachhilfe" in title || "mathe" in title
                || "physik" in title || "chemie" in title
                || "deutsch" in title || "englisch" in title
                || "biologie" in title || "latein" in title
                || "geschichte" in title               -> "https://images.unsplash.com/photo-1434030216411-0b793f4b4173?w=400&q=80"
        "programmier" in title || "pc" in title
                || "computer" in title || "kotlin" in title -> "https://images.unsplash.com/photo-1515879218367-8466d910aaa4?w=400&q=80"
        "mal" in title || "kunst" in title         -> "https://images.unsplash.com/photo-1513364776144-60967b0f800f?w=400&q=80"

        // Haushalt - spezifisch
        "putz" in title || "reinig" in title       -> "https://images.unsplash.com/photo-1581578731548-c64695cc6952?w=400&q=80"
        "umzug" in title                           -> "https://images.unsplash.com/photo-1600518464441-9154a4dea21b?w=400&q=80"
        "babysit" in title || "kinder" in title    -> "https://images.unsplash.com/photo-1503454537195-1dcabb73ffb9?w=400&q=80"
        "hund" in title || "tier" in title
                || "katze" in title || "pet" in title  -> "https://images.unsplash.com/photo-1587300003388-59208cc962cb?w=400&q=80"
        "möbel" in title || "aufbau" in title      -> "https://images.unsplash.com/photo-1555041469-a586c61ea9bc?w=400&q=80"
        "einkauf" in title                         -> "https://images.unsplash.com/photo-1534723452862-4c874986ebad?w=400&q=80"
        "fenster" in title                         -> "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400&q=80"

        // Handwerk - spezifisch
        "elektro" in title || "elektriker" in title
                || "steckdose" in title || "solar" in title -> "https://images.unsplash.com/photo-1621905251189-08b45d6a269e?w=400&q=80"
        "rohr" in title || "sanitär" in title
                || "wasser" in title                   -> "https://images.unsplash.com/photo-1585771724684-38269d6639fd?w=400&q=80"
        "mal" in title && listing.category == "Handwerk"
                || "streich" in title || "tapez" in title -> "https://images.unsplash.com/photo-1562259929-b4e1fd3aef09?w=400&q=80"
        "fahrrad" in title                         -> "https://images.unsplash.com/photo-1558618047-f4e80ee5b62e?w=400&q=80"
        "auto" in title                            -> "https://images.unsplash.com/photo-1520340356584-f9917d1eea6f?w=400&q=80"
        "schlüssel" in title                       -> "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400&q=80"

        // Garten - spezifisch
        "rasen" in title || "mäh" in title         -> "https://images.unsplash.com/photo-1558904541-efa843a96f01?w=400&q=80"
        "pflanz" in title || "blumen" in title
                || "gieß" in title                     -> "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400&q=80"
        "baum" in title || "hecke" in title
                || "schneid" in title                  -> "https://images.unsplash.com/photo-1599629954294-14df9ec8dfe8?w=400&q=80"

        // Kategorie Fallback
        listing.category == "Garten"   -> "https://images.unsplash.com/photo-1416879595882-3373a0480b5b?w=400&q=80"
        listing.category == "Bildung"  -> "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?w=400&q=80"
        listing.category == "Haushalt" -> "https://images.unsplash.com/photo-1558618666-fcd25c85cd64?w=400&q=80"
        listing.category == "Handwerk" -> "https://images.unsplash.com/photo-1581578731548-c64695cc6952?w=400&q=80"
        else -> "https://images.unsplash.com/photo-1560472354-b33ff0c44a43?w=400&q=80"
    }
}

@Composable
fun ListingCard(
    listing: Listing,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val accentColor = if (listing.isOffer) Green80 else Teal80
    val bgColor     = accentColor.copy(alpha = 0.12f)
    val imageUrl    = getImageUrlForListing(listing)

    Card(
        onClick  = onClick,
        shape    = RoundedCornerShape(16.dp),
        modifier = modifier,
        colors   = CardDefaults.cardColors(
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
            ) {
                if (imageUrl != null) {
                    AsyncImage(
                        model              = imageUrl,
                        contentDescription = listing.category,
                        contentScale       = ContentScale.Crop,
                        modifier           = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.15f))
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(bgColor)
                    )

                }

                // Kategorie Chip immer oben links
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
                        color      = Color.White,
                        fontSize   = 11.sp,
                        fontFamily = Nunito,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    listing.title,
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 11.sp,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis,
                    color      = MaterialTheme.colorScheme.onSurface
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier          = Modifier.padding(top = 1.dp)
                ) {
                    Icon(
                        imageVector        = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint               = accentColor,
                        modifier           = Modifier.size(11.dp)
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        listing.district,
                        fontFamily = Nunito,
                        fontSize   = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color      = accentColor
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
                    fontSize   = 10.sp,
                    color      = accentColor,
                    modifier   = Modifier.padding(top = 1.dp)
                )
            }
        }
    }
}