package com.cs407.lab9app

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.view.View

class DrawingLineView(
        context: Context,
        private val points: List<PointF>,
        private val lineColor: Int
) : View(context) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val pen = Paint().apply {
            color = lineColor
            strokeWidth = 8.0f
            style = Paint.Style.STROKE
        }

        val path = Path().apply {
            // Move the starting point of the path to the first point in the list
            moveTo(points[0].x, points[0].y)
            // Create a connected series of line segments
            for (point in points) {
                lineTo(point.x, point.y)
            }
            close()
        }

        canvas.drawPath(path, pen)
    }
}
