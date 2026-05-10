package com.example.gratzl.shared.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gratzl.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold),
    Font(R.font.nunito_black, FontWeight.Black),
)

val AppTypography = Typography(
    displaySmall   = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold,   fontSize = 36.sp),
    headlineMedium = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold,   fontSize = 28.sp),
    titleMedium    = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Bold,   fontSize = 16.sp),
    titleSmall     = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Medium, fontSize = 14.sp),
    bodyMedium     = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    bodySmall      = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 12.sp),
    labelSmall     = TextStyle(fontFamily = Nunito, fontWeight = FontWeight.Normal, fontSize = 11.sp),
)