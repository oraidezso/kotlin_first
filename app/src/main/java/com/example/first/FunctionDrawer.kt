package com.example.first

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import java.lang.Exception

class FunctionDrawer(imageView: ImageView) {
    private val iv=imageView

    fun drawFunction(f: (Double, Double) -> Double, minX: Double, maxX: Double, minY: Double, maxY: Double){
        if (minX >= maxX || minY >= maxY) return
        val fValues = calcFValues(f, minX, maxX, minY, maxY)
        val pixels = pixelateFValues(fValues)
        val bm2= Bitmap.createBitmap(pixels,iv.width, iv.height, Bitmap.Config.ARGB_8888)
        iv.setImageBitmap(bm2)
    }

    private fun calcFValues(f:(Double, Double)->Double, minX:Double, maxX:Double, minY:Double, maxY:Double): DoubleArray{
        val height=iv.height
        val width=iv.width

        var minVal = f(minX, minY)
        var maxVal = f(minX, minY)
        val stepX = (maxX - minX) / width
        val stepY = (maxY - minY) / height
        val fValues = DoubleArray(width*height)
        var x = minX
        var y = minY
        var akt: Double

        for (i in 0 until height) {
            for (j in 0 until width) {
                try {
                    akt = f(x, y)
                }catch (exception:Exception){
                    akt=0.0
                    Log.println(Log.ERROR,"function error","${exception.message}")
                }

                fValues[i*width+j]=akt
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
                fValues[i*width+j]  =(fValues[i*width+j] - minVal) / maxDifference
            }
        }
        return fValues
    }
    fun pixelateFValues(fValues:DoubleArray):IntArray{
        val height=iv.height
        val width= iv.width
        val normalizedFValues = IntArray(width*height)
        for (i in 0 until height) {
            for (j in 0 until width) {
                val normalizedValue=(fValues[i*width+j] * 255 ).toInt()
                normalizedFValues[i*width+j] = Color.argb(255,normalizedValue,normalizedValue,normalizedValue)
            }
        }
        return normalizedFValues
    }
}