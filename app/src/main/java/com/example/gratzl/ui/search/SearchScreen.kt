package com.example.gratzl.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.SampleData
import com.example.gratzl.data.model.SavedSearch
import com.example.gratzl.data.model.UrgencyTag
import com.example.gratzl.shared.components.BezirkDropdown
import com.example.gratzl.shared.components.GraetzelLogo
import com.example.gratzl.shared.components.ListingCard
import com.example.gratzl.shared.components.Switch as OfferSwitch
import com.example.gratzl.shared.components.SwitchAll
import com.example.gratzl.shared.theme.Green80
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    selectedBezirk: String,
    onBezirkChange: (String) -> Unit,
    onNavigateToListing: (Int) -> Unit,
    viewModel: SearchViewModel = viewModel()
) {
    val state      by viewModel.uiState.collectAsState()
    val categories  = listOf(null, "Bildung", "Haushalt", "Handwerk", "Garten")
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val cardWidth   = (screenWidth - 32.dp - 12.dp) / 2

    var showFilterSheet    by remember { mutableStateOf(false) }
    var showAddSearchSheet by remember { mutableStateOf(false) }

    var newSearchLabel    by remember { mutableStateOf("") }
    var newSearchCategory by remember { mutableStateOf<String?>(null) }
    var newSearchIsOffer  by remember { mutableStateOf(true) }
    var newSearchPrice    by remember { mutableStateOf(PriceType.FREE) }
    var newSearchUrgency  by remember { mutableStateOf(UrgencyTag.FLEXIBLE) }

    val activeFilterCount = listOf(
        state.selectedCategory != null,
        state.priceSortOrder != PriceSortOrder.NONE,
        state.distanceFilter  != DistanceFilter.ALL
    ).count { it }

    LaunchedEffect(selectedBezirk) {
        viewModel.onBezirkChange(selectedBezirk)
    }

    // Overlay — Neue gespeicherte Suche
    if (showAddSearchSheet) {
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
                    IconButton(onClick = {
                        showAddSearchSheet = false
                        newSearchLabel    = ""
                        newSearchCategory = null
                        newSearchIsOffer  = true
                        newSearchPrice    = PriceType.FREE
                        newSearchUrgency  = UrgencyTag.FLEXIBLE
                    }) {
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
                    Row(
                        verticalAlignment     = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier              = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.Filled.Info,
                            contentDescription = null,
                            tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier           = Modifier.size(16.dp)
                        )
                        Text(
                            text  = "Gespeicherte Suchen sind nur auf dem Home-Screen sichtbar, können aber hier und dort erstellt werden.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    OfferSwitch(
                        isOfferMode = newSearchIsOffer,
                        onToggle    = { newSearchIsOffer = it }
                    )

                    OutlinedTextField(
                        value         = newSearchLabel,
                        onValueChange = { newSearchLabel = it },
                        label         = { Text("Name der Suche") },
                        placeholder   = { Text("z.B. Nachhilfe Mathe") },
                        modifier      = Modifier.fillMaxWidth(),
                        shape         = RoundedCornerShape(15.dp),
                        singleLine    = true
                    )

                    var catExpanded by remember { mutableStateOf(false) }
                    val catList = listOf("Bildung", "Haushalt", "Handwerk", "Garten", "Sonstiges")

                    ExposedDropdownMenuBox(
                        expanded         = catExpanded,
                        onExpandedChange = { catExpanded = it }
                    ) {
                        OutlinedTextField(
                            value         = newSearchCategory ?: "Kategorie wählen",
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
                            catList.forEach { cat ->
                                DropdownMenuItem(
                                    text    = { Text(cat) },
                                    onClick = {
                                        newSearchCategory = cat
                                        catExpanded       = false
                                    }
                                )
                            }
                        }
                    }

                    Text("Bezahlung", style = MaterialTheme.typography.titleSmall)

                    val priceOptions = listOf(
                        PriceType.FREE     to "Kostenlos",
                        PriceType.TRADE    to "Tausch",
                        PriceType.COFFEE   to "Kaffee",
                        PriceType.FIXED    to "Preis",
                        PriceType.PER_HOUR to "Pro Std"
                    )
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        contentPadding        = PaddingValues(end = 8.dp)
                    ) {
                        items(priceOptions) { (type, label) ->
                            FilterChip(
                                selected = newSearchPrice == type,
                                onClick  = { newSearchPrice = type },
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
                                selected = newSearchUrgency == tag,
                                onClick  = { newSearchUrgency = tag },
                                label    = { Text(label) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick  = {
                            if (newSearchLabel.isNotBlank()) {
                                val icon = when (newSearchCategory) {
                                    "Bildung"  -> "📚"
                                    "Haushalt" -> "🏠"
                                    "Handwerk" -> "🔧"
                                    "Garten"   -> "🌿"
                                    else       -> "🔍"
                                }
                                SampleData.addSavedSearch(
                                    SavedSearch(
                                        id           = SampleData.nextSavedSearchId(),
                                        label        = newSearchLabel,
                                        categoryIcon = icon,
                                        query        = newSearchLabel,
                                        category     = newSearchCategory,
                                        isOffer      = newSearchIsOffer,
                                        priceType    = newSearchPrice,
                                        urgency      = newSearchUrgency
                                    )
                                )
                                showAddSearchSheet = false
                                newSearchLabel    = ""
                                newSearchCategory = null
                                newSearchIsOffer  = true
                                newSearchPrice    = PriceType.FREE
                                newSearchUrgency  = UrgencyTag.FLEXIBLE
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled  = newSearchLabel.isNotBlank(),
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

    // Filter BottomSheet
    if (showFilterSheet) {
        ModalBottomSheet(
            onDismissRequest = { showFilterSheet = false },
            sheetState       = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Filter", style = MaterialTheme.typography.titleLarge)

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
            Row(
                modifier              = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment     = Alignment.CenterVertically
            ) {
                GraetzelLogo()
                BezirkDropdown(
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

            Row(
                modifier              = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment     = Alignment.CenterVertically,
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

                FilledIconButton(
                    onClick = { showAddSearchSheet = true },
                    shape   = RoundedCornerShape(12.dp),
                    colors  = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Green80
                    )
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Gespeicherte Suche erstellen")
                }

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