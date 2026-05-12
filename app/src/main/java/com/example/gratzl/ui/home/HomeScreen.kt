package com.example.gratzl.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.data.model.Listing
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.UrgencyTag
import com.example.gratzl.shared.components.GraetzelLogo
import com.example.gratzl.shared.components.ListingCard
import com.example.gratzl.shared.components.Switch as OfferSwitch
import com.example.gratzl.shared.components.SwitchAll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    selectedBezirk: String,
    onBezirkChange: (String) -> Unit,
    onNavigateToListing: (Int) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Képernyő szélesség alapján számítjuk a kártya szélességét
    // Search: 2 oszlop, 16dp padding kétoldalt, 12dp köz = (width - 32 - 12) / 2
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth = (screenWidth - 32.dp - 12.dp) / 2

    LaunchedEffect(selectedBezirk) {
        viewModel.onDistrictSelected(selectedBezirk)
    }

    // Fullscreen overlay
    if (state.showAddSearchSheet) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color    = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier          = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.closeAddSearchSheet() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Zurück")
                    }
                    Text(
                        text  = "Neue gespeicherte Suche",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Column(
                    modifier            = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OfferSwitch(
                        isOfferMode = state.newSearchIsOffer,
                        onToggle    = { viewModel.onNewSearchIsOfferChange(it) }
                    )

                    OutlinedTextField(
                        value         = state.newSearchLabel,
                        onValueChange = { viewModel.onNewSearchLabelChange(it) },
                        label         = { Text("Name der Suche") },
                        placeholder   = { Text("z.B. Nachhilfe Mathe") },
                        modifier      = Modifier.fillMaxWidth(),
                        shape         = RoundedCornerShape(15.dp),
                        singleLine    = true
                    )

                    val categories = listOf("Bildung", "Haushalt", "Handwerk", "Garten", "Sonstiges")
                    var catExpanded by remember { mutableStateOf(false) }

                    ExposedDropdownMenuBox(
                        expanded         = catExpanded,
                        onExpandedChange = { catExpanded = it }
                    ) {
                        OutlinedTextField(
                            value         = state.newSearchCategory ?: "Kategorie wählen",
                            onValueChange = {},
                            readOnly      = true,
                            label         = { Text("Kategorie") },
                            trailingIcon  = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = catExpanded) },
                            modifier      = Modifier
                                .menuAnchor()
                                .fillMaxWidth(),
                            shape         = RoundedCornerShape(15.dp)
                        )
                        ExposedDropdownMenu(
                            expanded         = catExpanded,
                            onDismissRequest = { catExpanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text    = { Text(cat) },
                                    onClick = {
                                        viewModel.onNewSearchCategoryChange(cat)
                                        catExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    Text("Bezahlung", style = MaterialTheme.typography.titleSmall)
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding        = PaddingValues(end = 8.dp)
                    ) {
                        items(listOf(
                            PriceType.FREE     to "Kostenlos",
                            PriceType.TRADE    to "Tausch",
                            PriceType.COFFEE   to "Kaffee",
                            PriceType.FIXED    to "Preis",
                            PriceType.PER_HOUR to "Pro Std"
                        )) { (type, label) ->
                            FilterChip(
                                selected = state.newSearchPriceType == type,
                                onClick  = { viewModel.onNewSearchPriceTypeChange(type) },
                                label    = { Text(label) }
                            )
                        }
                    }

                    Text("Dringlichkeit", style = MaterialTheme.typography.titleSmall)
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        listOf(
                            UrgencyTag.FLEXIBLE to "Flexible",
                            UrgencyTag.TODAY    to "Today",
                            UrgencyTag.URGENT   to "Urgent"
                        ).forEach { (tag, label) ->
                            FilterChip(
                                selected = state.newSearchUrgency == tag,
                                onClick  = { viewModel.onNewSearchUrgencyChange(tag) },
                                label    = { Text(label) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick  = { viewModel.saveNewSearch() },
                        modifier = Modifier.fillMaxWidth(),
                        enabled  = state.newSearchLabel.isNotBlank(),
                        shape    = RoundedCornerShape(50.dp)
                    ) {
                        Text("Suche speichern")
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
        return
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
            modifier            = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding      = PaddingValues(bottom = 100.dp)
        ) {
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
                    onFavClick     = viewModel::toggleFavourite,
                    onShowAll      = { },
                    cardWidth      = cardWidth
                )
            }

            item {
                HomeSection(
                    title          = "In deiner Nähe",
                    listings       = state.nearby,
                    favourites     = state.favourites,
                    onListingClick = onNavigateToListing,
                    onFavClick     = viewModel::toggleFavourite,
                    onShowAll      = { },
                    cardWidth      = cardWidth
                )
            }

            item {
                Column {
                    Row(
                        modifier              = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment     = Alignment.CenterVertically
                    ) {
                        Text(
                            text  = "Gespeicherte Suchen",
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = { viewModel.openAddSearchSheet() }) {
                            Icon(Icons.Filled.Add, contentDescription = "Neue Suche")
                        }
                    }

                    if (state.savedSearches.isEmpty()) {
                        Text(
                            text     = "Noch keine gespeicherten Suchen",
                            style    = MaterialTheme.typography.bodySmall,
                            color    = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    } else {
                        LazyRow(
                            contentPadding        = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.savedSearches, key = { it.id }) { search ->
                                Card(
                                    onClick  = { },
                                    shape    = RoundedCornerShape(16.dp),
                                    modifier = Modifier.width(cardWidth)
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier         = Modifier
                                            .fillMaxWidth()
                                            .height(150.dp)
                                    ) {
                                        IconButton(
                                            onClick  = { viewModel.deleteSavedSearch(search.id) },
                                            modifier = Modifier
                                                .align(Alignment.TopEnd)
                                                .size(28.dp)
                                        ) {
                                            Icon(
                                                Icons.Filled.Close,
                                                contentDescription = "Löschen",
                                                modifier           = Modifier.size(16.dp),
                                                tint               = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                        Column(
                                            modifier            = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text      = search.categoryIcon,
                                                style     = MaterialTheme.typography.headlineLarge,
                                                textAlign = TextAlign.Center,
                                                modifier  = Modifier.fillMaxWidth()
                                            )
                                            Spacer(modifier = Modifier.height(8.dp))
                                            Text(
                                                text      = search.label,
                                                style     = MaterialTheme.typography.bodySmall,
                                                maxLines  = 1,
                                                textAlign = TextAlign.Center,
                                                modifier  = Modifier.fillMaxWidth()
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
}

@Composable
private fun HomeSection(
    title: String,
    listings: List<Listing>,
    favourites: Set<Int>,
    onListingClick: (Int) -> Unit,
    onFavClick: (Int) -> Unit,
    onShowAll: () -> Unit,
    cardWidth: androidx.compose.ui.unit.Dp
) {
    Column {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically
        ) {
            Text(
                text  = title,
                style = MaterialTheme.typography.titleMedium
            )
            TextButton(onClick = onShowAll) {
                Text(
                    text  = "Alles anzeigen",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

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
                            modifier = Modifier.width(cardWidth)
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