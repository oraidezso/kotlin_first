package com.example.first

import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    lateinit var drawer :FunctionDrawer
    private lateinit var mDetector: GestureDetectorCompat
    val functions = arrayOf<(Double, Double) -> Double>(
        { x, y -> Math.sin(y / x) },
        { x, y -> Math.sin(y * x) },
        { x, y -> Math.sin(y) + Math.cos(x) },
        { x, y -> Math.sin(x) * Math.cos(y) },
        { x, y -> Math.sin(y + x) },
        { x, y -> Math.sin(x * x + y * y) }
    )
    var current=0

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
//            Log.println(Log.ERROR,"${asd.height}","${asd.width}magic")
//            Log.println(Log.ERROR,"${asd.measuredHeight}","${asd.measuredWidth}magic")



            drawer.mode = 0
            drawer.K = 10
            drawer.drawFunction(functions[current], -10.0, 10.0, -10.0, 10.0)

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        drawer = FunctionDrawer(asd)
        mDetector = GestureDetectorCompat(this, MyGestureListener())
        fab.setOnClickListener {
//                view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            if(++current == functions.size) current=0
            onWindowFocusChanged(true)
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
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private inner class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(
            event1: MotionEvent,
            event2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            Log.e("tag","onFling: ${event1.x - event2.x}")
            //event1.x - event2.x
            if(event1.y>asd.height/2) {
                drawer.rotation = drawer.rotation - (event1.x - event2.x).toInt() / 10
                //Log.e("tag", "onFling: ${event1.y}")
            }
            else {
                drawer.rotation = drawer.rotation + (event1.x - event2.x).toInt() / 10
            }
            onWindowFocusChanged(true)
            return true
        }
    }
}
