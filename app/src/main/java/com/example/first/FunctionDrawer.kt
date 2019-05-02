package com.example.first

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.widget.ImageView

class FunctionDrawer(imageView: ImageView) {
    private val iv=imageView

    fun drawFunction(f: (Double, Double) -> Double, minX: Double, maxX: Double, minY: Double, maxY: Double){
        if (minX >= maxX || minY >= maxY) return
        val fValues = calcFValues(f, minX, maxX, minY, maxY)
        val bm2= Bitmap.createBitmap(fValues,iv.width, iv.height, Bitmap.Config.ARGB_8888)
        iv.setImageBitmap(bm2)
    }

    fun calcFValues(f:(Double,Double)->Double,minX:Double,maxX:Double,minY:Double,maxY:Double): IntArray{
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
                akt = f(x, y)
                fValues[i*width+j]=akt
                if (akt < minVal) minVal = akt
                if (akt > maxVal) maxVal = akt
                x += stepX
            }
            x = minX
            y += stepY
        }
        Log.println(Log.ERROR,"asd","magic")
        val maxDifference = maxVal - minVal
        val normalizedFValues = IntArray(width*height)


        for (i in 0 until height) {
            for (j in 0 until width) {
                val normalizedValue=((fValues[i*width+j] - minVal) * 255 / maxDifference).toInt()
                normalizedFValues[i*width+j] = Color.argb(255,normalizedValue,normalizedValue,normalizedValue)
            }
        }
        Log.println(Log.ERROR,"asd","magic")
        return normalizedFValues
    }
}