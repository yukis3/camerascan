package com.cs407.lab9app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class DrawingView(
        context: Context,
        private val boundingBox: Rect,
        private val label: String,
        private val boxColor: Int,
        private val textColor: Int
) : View(context) {

    private companion object {
        const val MAX_FONT_SIZE = 96F
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val pen = Paint().apply {
            textAlign = Paint.Align.LEFT
        }

        pen.color = boxColor
        pen.strokeWidth = 8F
        pen.style = Paint.Style.STROKE
        canvas.drawRect(boundingBox, pen)

        // Draw label
        val tagSize = Rect()

        // Set up paint for text and calculate the right font size
        pen.apply {
            style = Paint.Style.FILL_AND_STROKE
            color = textColor
            strokeWidth = 2F
            textSize = MAX_FONT_SIZE
        }

        pen.getTextBounds(label, 0, label.length, tagSize)
        var fontSize = pen.textSize * boundingBox.width() / tagSize.width()

        // Adjust the font size if needed
        if (fontSize < pen.textSize) {
            pen.textSize = fontSize
        }

        var margin = (boundingBox.width() - tagSize.width()) / 2.0F
        if (margin < 0F) margin = 0F

        // Draw the label text
        canvas.drawText(label, boundingBox.left + margin,
            (boundingBox.top + tagSize.height()).toFloat(), pen)
    }
}
