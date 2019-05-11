package com.example.first

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.widget.ImageView

class FunctionDrawer(imageView: ImageView) {
    private val iv = imageView
    var K = 20
        set(value) {
            field = if (value > 2) value else 2
        }
    var mode=0
        set(value) {field=if(value>1)0 else value}

    var rotation=0
        set(value) {field=value%360}

    fun drawFunction(f: (Double, Double) -> Double, minX: Double, maxX: Double, minY: Double, maxY: Double) {
        if (minX >= maxX || minY >= maxY) return
        val fValues = calcFValues(f, minX, maxX, minY, maxY)
        val pixels = if(mode==0) pixelsFromFValues(fValues)
            else myPixelsFromFValues(fValues)
        val bm2 = Bitmap.createBitmap(pixels, iv.width, iv.height, Bitmap.Config.ARGB_8888)
        iv.setImageBitmap(bm2)
    }

    private fun calcFValues(f: (Double, Double) -> Double, minX: Double, maxX: Double, minY: Double, maxY: Double): DoubleArray {
        val height = iv.height
        val width = iv.width
        var minVal = f(minX, minY)
        var maxVal = f(minX, minY)
        val stepX = (maxX - minX) / width
        val stepY = (maxY - minY) / height
        val fValues = DoubleArray(width * height)
        var x = minX
        var y = minY
        var akt: Double
        val rotc = Math.cos(rotation.toDouble()/360*2*Math.PI)
        val rots = Math.sin(rotation.toDouble()/360*2*Math.PI)

        for (i in 0 until height) {
            for (j in 0 until width) {
                try {
                    val rotx= x*rotc-y*rots
                    val roty= x*rots+y*rotc
                    akt = f(rotx, roty)
                } catch (exception: Exception) {
                    akt = 0.0
                    Log.println(Log.ERROR, "function error", "${exception.message}")
                }

                fValues[i * width + j] = akt
                if (akt < minVal) minVal = akt
                if (akt > maxVal) maxVal = akt
                x += stepX
            }
            x = minX
            y += stepY
        }

        val maxDifference = maxVal - minVal

        for (i in 0 until height) {
            for (j in 0 until width) {
                fValues[i * width + j] = (fValues[i * width + j] - minVal) / maxDifference
            }
        }
        return fValues
    }

    private fun pixelsFromFValues(fValues: DoubleArray): IntArray {
        val height = iv.height
        val width = iv.width
        val pixels = IntArray(width * height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                val isBlack = ((fValues[i * width + j] * K).toInt()) % 2 == 0
                pixels[i * width + j] = if(isBlack)Color.BLACK else Color.WHITE
            }
        }
        return pixels
    }

    private fun myPixelsFromFValues(fValues: DoubleArray): IntArray {
        val height = iv.height
        val width = iv.width
        val pixels = IntArray(width * height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                val colorValue = (fValues[i * width + j] * 255).toInt()
                pixels[i * width + j] = Color.argb(
                    255,
                    colorValue,
                    colorValue,
                    colorValue
                )
            }
        }
        return pixels
    }
}