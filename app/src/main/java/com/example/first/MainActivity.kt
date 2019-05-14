package com.example.first

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.graphics.Point


class MainActivity : AppCompatActivity() {
    lateinit var drawer: FunctionDrawer
    private lateinit var mDetector: GestureDetectorCompat
    private val functions = arrayOf<(Double, Double) -> Double>(
        { x, y -> Math.sin(y / x) },
        { x, y -> x * y },
        { x, y -> x * x + y * y },
        { x, y -> Math.sin(y * x) },
        { x, y -> Math.sin(y) + Math.cos(x) },
        { x, y -> Math.sin(x) * Math.cos(y) },
        { x, y -> Math.sin(y + x) },
        { x, y -> Math.sin(x * x + y * y) }
    )
    private var current = 0
    private var maxX = 3.0

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
        drawer.mode = 1
        drawer.K = 10
        mDetector = GestureDetectorCompat(this, RotationListener())
        fab.setOnClickListener {
            //                view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            if (++current == functions.size) current = 0
            reDraw()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @SuppressLint("ResourceType")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.preferences -> {
                var settingsFragment = SettingsFragment();
                val args =  Bundle();
                settingsFragment.arguments = args;
                supportFragmentManager.beginTransaction().replace(content_main.id,settingsFragment).commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
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
            drawer.rotation += if (x1 * y2 - x2 * y1 < 0) angle.toInt() else - angle.toInt()
            reDraw()
            return true
        }
    }
}
