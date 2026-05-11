package com.example.gratzl.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.shared.components.AppTopBar
import com.example.gratzl.shared.components.ListingCard

@Composable
fun SavedListingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToListing: (Int) -> Unit
) {
    val savedListings = SampleData.savedListings

    Scaffold(
        topBar = {
            AppTopBar(
                title = "Gespeicherte Anzeigen",
                showBack = true,
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(savedListings, key = { it.id }) { listing ->
                ListingCard(
                    listing = listing,
                    onClick = { onNavigateToListing(listing.id) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}