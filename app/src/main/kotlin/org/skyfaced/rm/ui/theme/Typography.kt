package org.skyfaced.rm.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.skyfaced.rm.R

val NotoSans = FontFamily(
    Font(R.font.noto_sans_black, FontWeight.Black),
    Font(R.font.noto_sans_black_italic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.noto_sans_bold, FontWeight.Bold),
    Font(R.font.noto_sans_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.noto_sans_extra_bold, FontWeight.ExtraBold),
    Font(R.font.noto_sans_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.noto_sans_extra_light, FontWeight.ExtraLight),
    Font(R.font.noto_sans_extra_light_italic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.noto_sans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.noto_sans_light, FontWeight.Light),
    Font(R.font.noto_sans_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.noto_sans_medium, FontWeight.Medium),
    Font(R.font.noto_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.noto_sans_regular),
    Font(R.font.noto_sans_semi_bold, FontWeight.SemiBold),
    Font(R.font.noto_sans_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.noto_sans_thin, FontWeight.Thin),
    Font(R.font.noto_sans_thin_italic, FontWeight.Thin, FontStyle.Italic),
)

private val displayLarge = TextStyle(
    fontSize = 57.sp,
    lineHeight = 64.sp,
    letterSpacing = (-0.25).sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val displayMedium = TextStyle(
    fontSize = 45.sp,
    lineHeight = 52.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val displaySmall = TextStyle(
    fontSize = 36.sp,
    lineHeight = 44.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val headlineLarge = TextStyle(
    fontSize = 32.sp,
    lineHeight = 40.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val headlineMedium = TextStyle(
    fontSize = 28.sp,
    lineHeight = 36.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val headlineSmall = TextStyle(
    fontSize = 24.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val titleLarge = TextStyle(
    fontSize = 22.sp,
    lineHeight = 28.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val titleMedium = TextStyle(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.1.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val titleSmall = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val bodyLarge = TextStyle(
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.5.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val bodyMedium = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.25.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val bodySmall = TextStyle(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val labelLarge = TextStyle(
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.1.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val labelMedium = TextStyle(
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)
private val labelSmall = TextStyle(
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp,
    fontWeight = FontWeight.Medium,
    fontStyle = FontStyle.Normal,
    fontFamily = NotoSans
)

val RMTypography = Typography(
    displayLarge = displayLarge,
    displayMedium = displayMedium,
    displaySmall = displaySmall,
    headlineLarge = headlineLarge,
    headlineMedium = headlineMedium,
    headlineSmall = headlineSmall,
    titleLarge = titleLarge,
    titleMedium = titleMedium,
    titleSmall = titleSmall,
    bodyLarge = bodyLarge,
    bodyMedium = bodyMedium,
    bodySmall = bodySmall,
    labelLarge = labelLarge,
    labelMedium = labelMedium,
    labelSmall = labelSmall
)