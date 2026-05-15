package com.example.gratzl.shared.components

import com.example.gratzl.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratzl.shared.theme.Nunito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GraetzelLogo(
    selectedBezirk: String = "1070 · Neubau",
    onBezirkChange: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val bezirke = listOf(
        "1010 · Innere Stadt", "1020 · Leopoldstadt", "1030 · Landstraße",
        "1040 · Wieden", "1050 · Margareten", "1060 · Mariahilf",
        "1070 · Neubau", "1080 · Josefstadt", "1090 · Alsergrund",
        "1100 · Favoriten", "1110 · Simmering", "1120 · Meidling",
        "1130 · Hietzing", "1140 · Penzing", "1150 · Rudolfsheim",
        "1160 · Ottakring", "1170 · Hernals", "1180 · Währing",
        "1190 · Döbling", "1200 · Brigittenau", "1210 · Floridsdorf",
        "1220 · Donaustadt", "1230 · Liesing"
    )
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFF2A6B45))
        ) {
            Text(
                "G",
                fontFamily = Nunito,
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Black
            )
        }

        Spacer(Modifier.width(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(-4.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "Gr",
                    fontFamily = Nunito,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-1).sp
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_graetzel_house),
                    contentDescription = null,
                    modifier = Modifier.size(width = 22.dp, height = 30.dp)
                )
                Text(
                    "tzel",
                    fontFamily = Nunito,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-1).sp
                )
            }

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable { expanded = true }
                ) {
                    Icon(
                        Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = Color(0xFF2A6B45),
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(3.dp))
                    Text(
                        selectedBezirk,
                        fontFamily = Nunito,
                        color = Color(0xFF2A6B45),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color(0xFF2A6B45),
                        modifier = Modifier.size(16.dp)
                    )
                }
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    bezirke.forEach { b ->
                        DropdownMenuItem(
                            text = { Text(b, fontSize = 16.sp) },
                            onClick = {
                                onBezirkChange(b)
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}