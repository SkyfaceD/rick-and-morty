package org.skyfaced.rm.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

private val RMSmallestRoundedCornerShape = RoundedCornerShape(4.dp)
private val RMSmallRoundedCornerShape = RoundedCornerShape(8.dp)
private val RMDefaultRoundedCornerShape = RoundedCornerShape(12.dp)
private val RMBigRoundedCornerShape = RoundedCornerShape(16.dp)
private val RMBiggestRoundedCornerShape = RoundedCornerShape(32.dp)

val RMShapes = Shapes(
    extraSmall = RMSmallestRoundedCornerShape,
    small = RMSmallRoundedCornerShape,
    medium = RMDefaultRoundedCornerShape,
    large = RMBigRoundedCornerShape,
    extraLarge = RMBiggestRoundedCornerShape
)