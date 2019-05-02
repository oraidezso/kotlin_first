package com.example.first

import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.graphics.Bitmap



class Drawer(bitmap: Bitmap) {
    private val bm: Bitmap = bitmap
    private val canvas = Canvas(bm)
    fun test() {
        val rect = ShapeDrawable(RectShape())
        rect.setBounds(100, 200, 300, 400)
        rect.paint.color = Color.RED
        rect.draw(canvas)

        val paint = Paint()
        paint.color = Color.RED
        canvas.drawRect(100.0F, 500.0F, 300.0F, 600.0F, paint)

    }

    fun drawTriangle(p1: Point, p2: Point, p3: Point, color: Int) {
        val paint = Paint()
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

    fun drawRectangle(p1: Point, p2: Point, p3: Point, p4: Point, color: Int) {
        val paint = Paint()
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






}