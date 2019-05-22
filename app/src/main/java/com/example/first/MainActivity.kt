package com.example.first

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.graphics.Point
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.*


class MainActivity : AppCompatActivity() {
    lateinit var drawer: FunctionDrawer
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var mScaleDetector:ScaleGestureDetector
    private val functions = arrayOf<(Double, Double) -> Double>(
        { x, y -> Math.sin(y) + Math.cos(x) },
        { x, y -> Math.sin(y / x) },
        { x, y -> x * y },
        { x, y -> x * x + y * y },
        { x, y -> Math.sin(y * x) },
        { x, y -> Math.sin(y + x) },
        { x, y -> Math.sin(x * x + y * y) }
    )
    private var current = 0
    private var maxX = 3.0
    var mode=0

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            reDraw()
        }
    }

    fun reDraw() {
        val maxY = maxX * asd.height.toDouble() / asd.width.toDouble()
        drawer.drawFunction(functions[current], -maxX, maxX, -maxY, maxY)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawer = FunctionDrawer(asd)
        drawer.mode = mode
        drawer.K = 10
        mDetector = GestureDetectorCompat(this, RotationListener())
        mScaleDetector=ScaleGestureDetector(this,PinchListener())
        fab.setOnClickListener {
//                            view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            //if (++current == functions.size) current = 0
            drawer.K++
            reDraw()
        }
        fab2.setOnClickListener {
            if(drawer.K>3)drawer.K--
            reDraw()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.function_0 -> {current=0; return true}
            R.id.function_1 -> {current=1; return true}
            R.id.function_2 -> {current=2; return true}
            R.id.function_3 -> {current=3; return true}
            R.id.function_4 -> {current=4; return true}
            R.id.function_5 -> {current=5; return true}
            R.id.function_6 -> {current=6; return true}
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        mScaleDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private inner class RotationListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val size = Point()
            windowManager.defaultDisplay.getSize(size)
            val height = size.y
            val y1 = event1.y + asd.height / 2 - height
            val y2 = event2.y + asd.height / 2 - height
            val x1 = event1.x - asd.width / 2
            val x2 = event2.x - asd.width / 2
            val scalar = (y1 * y2 + x1 * x2).toDouble()
            val length = Math.sqrt((x1 * x1 + y1 * y1) * (x2 * x2 + y2 * y2).toDouble())
            val angle = Math.acos(scalar / length) * 360 / (2 * Math.PI)
            if (x1 * y2 - x2 * y1 > 0) drawer.rotation -= angle.toInt()
            else drawer.rotation += angle.toInt()
            reDraw()
            return true
        }
    }

     inner class PinchListener : ScaleGestureDetector.SimpleOnScaleGestureListener (){

         override fun onScaleEnd(detector: ScaleGestureDetector?) {
             if (detector != null) {
                 maxX/=detector.scaleFactor
                 reDraw()
                 Log.println(Log.ERROR, "scale factor", "${detector.scaleFactor}")
                 Log.println(Log.ERROR, "function error", "${detector.focusX}")
             }


             super.onScaleEnd(detector)
         }
     }


}
