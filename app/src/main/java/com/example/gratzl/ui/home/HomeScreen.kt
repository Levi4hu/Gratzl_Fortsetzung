package com.example.gratzl.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.shared.components.GraetzelLogo
import com.example.gratzl.shared.components.ListingCard
import com.example.gratzl.shared.components.SwitchAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    selectedBezirk : String,
    onBezirkChange : (String) -> Unit,
    onNavigateToListing: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(selectedBezirk) {
        viewModel.onDistrictSelected(selectedBezirk)
    }

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                GraetzelLogo(
                    selectedBezirk = selectedBezirk,
                    onBezirkChange = { newBezirk ->
                        onBezirkChange(newBezirk)
                        viewModel.onDistrictSelected(newBezirk)
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding      = PaddingValues(bottom = 100.dp)
        ) {
            // Alle / Angebote / Anfragen switch
            item {
                SwitchAll(
                    filter         = state.filter,
                    onFilterChange = viewModel::onFilterChange,
                    modifier       = Modifier.padding(horizontal = 16.dp)
                )
            }

            item {
                HomeSection(
                    title          = "Für dich ausgewählt",
                    listings       = state.forYou,
                    favourites     = state.favourites,
                    onListingClick = onNavigateToListing,
                    onFavClick     = viewModel::toggleFavourite
                )
            }

            item {
                HomeSection(
                    title          = "In deiner Nähe",
                    listings       = state.nearby,
                    favourites     = state.favourites,
                    onListingClick = onNavigateToListing,
                    onFavClick     = viewModel::toggleFavourite
                )
            }

            item {
                Column {
                    Text(
                        text     = "Gespeicherte Suchen",
                        style    = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                    )
                    LazyRow(
                        contentPadding        = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.height(130.dp)
                    ) {
                        items(state.savedSearches, key = { it.id }) { search ->
                            Card(
                                onClick  = { },
                                shape    = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .width(120.dp)
                                    .height(100.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            search.categoryIcon,
                                            style = MaterialTheme.typography.headlineMedium,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                        // ↑ kikommentelni ha nem kell ikon: // Text(search.categoryIcon, ...)
                                        Text(
                                            text = search.label,
                                            style = MaterialTheme.typography.bodySmall,
                                            maxLines = 1,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeSection(
    title: String,
    listings: List<Listing>,
    favourites: Set<Int>,
    onListingClick: (Int) -> Unit,
    onFavClick: (Int) -> Unit
) {
    Column {
        Text(
            text     = title,
            style    = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
        )
        if (listings.isEmpty()) {
            Text(
                text     = "Keine Anzeigen gefunden",
                style    = MaterialTheme.typography.bodySmall,
                color    = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            LazyRow(
                contentPadding        = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(listings, key = { it.id }) { listing ->
                    Box {
                        ListingCard(
                            listing  = listing,
                            onClick  = { onListingClick(listing.id) },
                            modifier = Modifier.width(160.dp)
                        )
                        IconButton(
                            onClick  = { onFavClick(listing.id) },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector        = if (listing.id in favourites)
                                    Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = "Favorit",
                                tint               = if (listing.id in favourites)
                                    Color.Red else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}