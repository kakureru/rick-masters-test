package com.rickmasters.common.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rickmasters.common.ui.R

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.circe_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 21.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circe_light)),
        fontWeight = FontWeight.Light,
        fontSize = 21.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.circe_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.circe_light)),
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    ),
)