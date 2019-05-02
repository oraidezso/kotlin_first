package com.example.first

import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape


class Drawer(bitmap: Bitmap) {
    private val bm: Bitmap = bitmap
    private val canvas = Canvas(bm)
    fun test() {
        var rect = ShapeDrawable(RectShape())
        rect.setBounds(100, 200, 300, 400)
        rect.paint.color = Color.RED
        rect.draw(canvas)

        var paint = Paint()
        paint.color = Color.RED
        canvas.drawRect(100.0F, 500.0F, 300.0F, 600.0F, paint)
//        for (i in 0 until bm.width) {
//            for (j in 0 until bm.height step 20) {
//                bm.setPixel(i, j, Color.RED)
//            }
//        }
//        for (i in 0 until bm.width step 20) {
//            for (j in 0 until bm.height) {
//                bm.setPixel(i, j, Color.RED)
//            }
//        }
        var asd = Rect()
    }

    fun triangle(p1: Point, p2: Point, p3: Point, color: Int) {
        var paint = Paint()
        paint.color = color
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true

        val path = Path()
        path.moveTo(p1.x.toFloat(), p1.y.toFloat())
        path.lineTo(p2.x.toFloat(), p2.y.toFloat())
        path.lineTo(p3.x.toFloat(), p3.y.toFloat())
        path.lineTo(p1.x.toFloat(), p1.y.toFloat())
        path.close()

        canvas.drawPath(path, paint)
    }
    fun rectangle(p1: Point, p2: Point, p3: Point, p4: Point, color: Int) {
        var paint = Paint()
        paint.color = color
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.isAntiAlias = true

        val path = Path()
        path.moveTo(p1.x.toFloat(), p1.y.toFloat())
        path.lineTo(p2.x.toFloat(), p2.y.toFloat())
        path.lineTo(p3.x.toFloat(), p3.y.toFloat())
        path.lineTo(p4.x.toFloat(), p4.y.toFloat())
        path.close()

        canvas.drawPath(path, paint)
    }

    var minX: Int = 0
    var minY: Int = 0
    var maxX: Int = 0
    var maxY: Int = 0
    private fun ScaleToBitmap(x: Double, y: Double): Point {
        val stretch = bm.width / (maxX - minX)
        val pX = ((x - minX) * stretch)
        val pY = ((y - minY) * stretch * -1) + bm.height - 1
        return Point(pX.toInt(), pY.toInt())
    }
}