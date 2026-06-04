package com.example.gratzl.shared.components

import com.example.gratzl.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun GraetzelLogo(
    modifier: Modifier = Modifier
) {
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
                fontFamily  = Nunito,
                color       = Color.White,
                fontSize    = 32.sp,
                fontWeight  = FontWeight.Black
            )
        }

        Spacer(Modifier.width(12.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "Gr",
                fontFamily    = Nunito,
                color         = MaterialTheme.colorScheme.onBackground,
                fontSize      = 32.sp,
                fontWeight    = FontWeight.Black,
                letterSpacing = (-1).sp
            )
            Image(
                painter            = painterResource(id = R.drawable.ic_graetzel_house),
                contentDescription = null,
                modifier           = Modifier.size(width = 22.dp, height = 30.dp)
            )
            Text(
                "tzel",
                fontFamily    = Nunito,
                color         = MaterialTheme.colorScheme.onBackground,
                fontSize      = 32.sp,
                fontWeight    = FontWeight.Black,
                letterSpacing = (-1).sp
            )
        }
    }
}