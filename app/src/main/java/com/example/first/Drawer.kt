package com.example.first

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point

class Drawer(bitmap:Bitmap){
    private val bm:Bitmap = bitmap

    fun test(){
        for (i in 0 until bm.width) {
            for (j in 0 until bm.height step 20){
                bm.setPixel(i,j, Color.RED)
            }
        }
        for (i in 0 until bm.width step 20) {
            for (j in 0 until bm.height ){
                bm.setPixel(i,j, Color.RED)
            }
        }
    }

    var minX:Int = 0
    var minY:Int = 0
    var maxX:Int = 0
    var maxY:Int = 0
    private fun ScaleToBitmap(x: Double, y: Double): Point {
        val stretch = bm.width / (maxX - minX)
        val pX = ((x - minX) * stretch) as Int
        val pY = ((y - minY) * stretch * -1) as Int + bm.height - 1
        return Point(pX, pY)
    }
}