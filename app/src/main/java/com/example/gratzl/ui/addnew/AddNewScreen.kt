package com.example.gratzl.ui.addnew

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import com.example.gratzl.data.model.PriceType
import com.example.gratzl.data.model.UrgencyTag
import com.example.gratzl.shared.components.AppTopBar
import com.example.gratzl.shared.components.Switch
import com.example.gratzl.shared.theme.MarktplatzTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNewScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddNewViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    // Wenn Anzeige erfolgreich aufgegeben → zurück navigieren
    LaunchedEffect(state.isSubmitted) {
        if (state.isSubmitted) {
            viewModel.resetSubmit()
            onNavigateBack()
        }
    }

    Scaffold(
        topBar = {
            AppTopBar(
                title       = "Neue Anzeige",
                showBack    = true,
                onBackClick = onNavigateBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Ich biete / Ich suche
            Switch(
                isOfferMode = state.isOffer,
                onToggle    = { viewModel.onIsOfferChange(it) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Titel
            OutlinedTextField(
                value         = state.title,
                shape         = RoundedCornerShape(15.dp),
                onValueChange = { viewModel.onTitleChange(it) },
                label         = { Text("Titel") },
                placeholder   = { Text("Min. 10 Zeichen") },
                modifier      = Modifier.fillMaxWidth(),
                isError       = state.titleError != null,
                supportingText = {
                    if (state.titleError != null) {
                        Text(state.titleError!!, color = MaterialTheme.colorScheme.error)
                    } else {
                        Text("${state.title.length} Zeichen")
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Kategorie
            val categories = listOf("Bildung", "Haushalt", "Handwerk", "Garten", "Sonstiges")
            var categoryExpanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded         = categoryExpanded,
                onExpandedChange = { categoryExpanded = it }
            ) {
                OutlinedTextField(
                    value             = state.category ?: "Kategorie wählen",
                    shape         = RoundedCornerShape(15.dp),
                    onValueChange     = {},
                    readOnly          = true,
                    label             = { Text("Kategorie") },
                    trailingIcon      = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                    modifier          = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(
                    expanded         = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false }
                ) {
                    categories.forEach { cat ->
                        DropdownMenuItem(
                            text    = { Text(cat) },
                            onClick = {
                                viewModel.onCategorySelected(cat)
                                categoryExpanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Beschreibung
            OutlinedTextField(
                value         = state.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label         = { Text("Beschreibung") },
                shape         = RoundedCornerShape(15.dp),
                modifier      = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                maxLines      = 5
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Preis Typ
            Text("Bezahlung", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(6.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp),
                    contentPadding = PaddingValues(end = 8.dp)
                ){
                items(
                listOf(
                    PriceType.FREE     to "Kostenlos",
                    PriceType.TRADE    to "Tausch",
                    PriceType.COFFEE   to "Kaffee",
                    PriceType.FIXED    to "Preis",
                    PriceType.PER_HOUR to "Pro Std"
                )
                ){ (type, label) ->
                    FilterChip(
                        selected = state.priceType == type,
                        onClick  = { viewModel.onPriceTypeSelected(type) },
                        label    = { Text(label) }
                    )
                }

            }

            // Preis Eingabe — nur wenn FIXED oder PER_HOUR
            if (state.priceType == PriceType.FIXED || state.priceType == PriceType.PER_HOUR) {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value         = state.price,
                    onValueChange = { viewModel.onPriceChange(it) },
                    label         = { Text("Preis in €") },
                    modifier      = Modifier.fillMaxWidth(),
                    singleLine    = true
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Dringlichkeit
            Text("Dringlichkeit", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(6.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf(
                    UrgencyTag.FLEXIBLE to "Flexible",
                    UrgencyTag.TODAY    to "Today",
                    UrgencyTag.URGENT   to "Urgent"
                ).forEach { (tag, label) ->
                    FilterChip(
                        selected = state.urgency == tag,
                        onClick  = { viewModel.onUrgencySelected(tag) },
                        label    = { Text(label) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            Button(
                onClick  = { viewModel.onSubmit() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Anzeige aufgeben")
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AddNewScreenPreview() {
    MarktplatzTheme {
        AddNewScreen(onNavigateBack = {})
    }
}