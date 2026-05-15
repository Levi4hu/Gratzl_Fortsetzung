package com.example.gratzl.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.shared.components.GraetzelLogo
import com.example.gratzl.shared.components.ListingCard
import com.example.gratzl.shared.components.SwitchAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    selectedBezirk: String,
    onBezirkChange: (String) -> Unit,
    onNavigateToListing: (Int) -> Unit,
    viewModel: SearchViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val categories = listOf(null, "Bildung", "Haushalt", "Handwerk", "Garten")

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth   = (screenWidth - 32.dp - 12.dp) / 2

    var showFilterSheet by remember { mutableStateOf(false) }

    val activeFilterCount = listOf(
        state.selectedCategory != null,
        state.priceSortOrder != PriceSortOrder.NONE,
        state.distanceFilter != DistanceFilter.ALL
    ).count { it }

    LaunchedEffect(selectedBezirk) {
        viewModel.onBezirkChange(selectedBezirk)
    }

    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState       = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier            = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Filter", style = MaterialTheme.typography.titleLarge)

                // Kategorie
                Text("Kategorie", style = MaterialTheme.typography.titleSmall)
                LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(count = categories.size) { index ->
                        val cat = categories[index]
                        FilterChip(
                            selected = state.selectedCategory == cat,
                            onClick  = { viewModel.onCategorySelected(cat) },
                            label    = { Text(cat ?: "Alle") }
                        )
                    }
                }

                // Preis
                Text("Preis", style = MaterialTheme.typography.titleSmall)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    FilterChip(
                        selected = state.priceSortOrder == PriceSortOrder.NONE,
                        onClick  = { viewModel.onPriceSortOrderChange(PriceSortOrder.NONE) },
                        label    = { Text("Standard") }
                    )
                    FilterChip(
                        selected = state.priceSortOrder == PriceSortOrder.ASC,
                        onClick  = { viewModel.onPriceSortOrderChange(PriceSortOrder.ASC) },
                        label    = { Text("Günstigste zuerst") }
                    )
                    FilterChip(
                        selected = state.priceSortOrder == PriceSortOrder.DESC,
                        onClick  = { viewModel.onPriceSortOrderChange(PriceSortOrder.DESC) },
                        label    = { Text("Teuerste zuerst") }
                    )
                }

                // Distanz
                Text("Entfernung", style = MaterialTheme.typography.titleSmall)
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    DistanceFilter.values().forEach { dist ->
                        val label = when (dist) {
                            DistanceFilter.ALL      -> "Alle"
                            DistanceFilter.NEAR_1KM -> "< 1 km"
                            DistanceFilter.NEAR_3KM -> "< 3 km"
                            DistanceFilter.NEAR_5KM -> "< 5 km"
                        }
                        FilterChip(
                            selected = state.distanceFilter == dist,
                            onClick  = { viewModel.onDistanceFilterChange(dist) },
                            label    = { Text(label) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Reset
                if (activeFilterCount > 0) {
                    OutlinedButton(
                        onClick  = {
                            viewModel.onCategorySelected(null)
                            viewModel.onPriceSortOrderChange(PriceSortOrder.NONE)
                            viewModel.onDistanceFilterChange(DistanceFilter.ALL)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape    = RoundedCornerShape(50.dp)
                    ) {
                        Text("Filter zurücksetzen")
                    }
                }
            }
        }
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
                        viewModel.onBezirkChange(newBezirk)
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            SwitchAll(
                filter         = state.filter,
                onFilterChange = viewModel::onFilterChange,
                allCount       = state.allCount,
                offerCount     = state.offerCount,
                requestCount   = state.requestCount,
                modifier       = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Suchfeld + Filter Button
            Row(
                modifier          = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value         = state.query,
                    onValueChange = { viewModel.onQueryChange(it) },
                    placeholder   = { Text("Suche Dienstleistung...") },
                    leadingIcon   = { Icon(Icons.Filled.Search, contentDescription = null) },
                    modifier      = Modifier.weight(1f),
                    singleLine    = true,
                    shape         = RoundedCornerShape(50.dp)
                )

                BadgedBox(
                    badge = {
                        if (activeFilterCount > 0) {
                            Badge { Text("$activeFilterCount") }
                        }
                    }
                ) {
                    FilledIconButton(
                        onClick = { showFilterSheet = true },
                        shape   = RoundedCornerShape(12.dp)
                    ) {
                        Icon(Icons.Filled.Tune, contentDescription = "Filter")
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (state.results.isEmpty()) {
                Box(
                    modifier         = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Keine Ergebnisse gefunden",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns               = GridCells.Fixed(2),
                    verticalArrangement   = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    contentPadding        = PaddingValues(
                        start  = 16.dp,
                        end    = 16.dp,
                        bottom = 100.dp
                    ),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(items = state.results, key = { it.id }) { listing ->
                        Box {
                            ListingCard(
                                listing  = listing,
                                onClick  = { onNavigateToListing(listing.id) },
                                modifier = Modifier.width(cardWidth)
                            )
                            IconButton(
                                onClick  = { viewModel.toggleFavourite(listing.id) },
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector        = if (listing.id in state.favourites)
                                        Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                    contentDescription = "Favorit",
                                    tint               = if (listing.id in state.favourites)
                                        Color.Red else MaterialTheme.colorScheme.onSurface
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}