package com.pdmproyecto.mymedicine.utils

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.withRotation


class VerticalLabelDrawer(
    private val labelTextSize: TextUnit = 20.sp,
    private val labelTextColor: Color = Color.Black,
    private val labelSpacing: Dp = (-16).dp
) : LabelDrawer {

    private val paint = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
    }
    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {
        drawScope.apply {
            paint.textSize = labelTextSize.toPx()
            paint.color = labelTextColor.toArgb()

            val centerX = barArea.left + barArea.width / 2
            val startY = barArea.bottom + labelSpacing.toPx()

            val nativeCanvas = drawContext.canvas.nativeCanvas

            nativeCanvas.withRotation(-90f , centerX , startY) {
                drawText(label , centerX , startY , paint)
            }
        }
    }
}