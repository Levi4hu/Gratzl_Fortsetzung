package com.example.gratzl.shared.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gratzl.shared.theme.Green80
import com.example.gratzl.shared.theme.Nunito
import com.example.gratzl.shared.theme.Teal80
import com.example.gratzl.ui.home.HomeFilter

@Composable
fun SwitchAll(
    filter: HomeFilter,
    onFilterChange: (HomeFilter) -> Unit,
    allCount: Int = 0,
    offerCount: Int = 0,
    requestCount: Int = 0,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {

        Button(
            onClick         = { onFilterChange(HomeFilter.ALL) },
            modifier        = Modifier.weight(1f),
            shape           = RoundedCornerShape(12.dp),
            contentPadding  = PaddingValues(horizontal = 6.dp, vertical = 10.dp),
            colors          = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ALL)
                    Color.Gray else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "Alle",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 12.sp,
                    color      = if (filter == HomeFilter.ALL)
                        Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (allCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (filter == HomeFilter.ALL)
                            Color.White.copy(alpha = 0.25f) else Color.Gray.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "$allCount",
                            modifier   = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                            fontSize   = 10.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color      = if (filter == HomeFilter.ALL) Color.White else Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Button(
            onClick        = { onFilterChange(HomeFilter.ANGEBOTE) },
            modifier       = Modifier.weight(1f),
            shape          = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 10.dp),
            colors         = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ANGEBOTE)
                    Green80 else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "Angebote",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 12.sp,
                    color      = if (filter == HomeFilter.ANGEBOTE)
                        Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (offerCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (filter == HomeFilter.ANGEBOTE)
                            Color.White.copy(alpha = 0.25f) else Green80.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "$offerCount",
                            modifier   = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                            fontSize   = 10.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color      = if (filter == HomeFilter.ANGEBOTE) Color.White else Green80
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.width(6.dp))

        Button(
            onClick        = { onFilterChange(HomeFilter.ANFRAGEN) },
            modifier       = Modifier.weight(1f),
            shape          = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 6.dp, vertical = 10.dp),
            colors         = ButtonDefaults.buttonColors(
                containerColor = if (filter == HomeFilter.ANFRAGEN)
                    Teal80 else MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    "Anfragen",
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Bold,
                    fontSize   = 12.sp,
                    color      = if (filter == HomeFilter.ANFRAGEN)
                        Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                )
                if (requestCount > 0) {
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = if (filter == HomeFilter.ANFRAGEN)
                            Color.White.copy(alpha = 0.25f) else Teal80.copy(alpha = 0.15f)
                    ) {
                        Text(
                            "$requestCount",
                            modifier   = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                            fontSize   = 10.sp,
                            fontFamily = Nunito,
                            fontWeight = FontWeight.Bold,
                            color      = if (filter == HomeFilter.ANFRAGEN) Color.White else Teal80
                        )
                    }
                }
            }
        }
    }
}