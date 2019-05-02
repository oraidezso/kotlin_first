package com.example.first

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        toolbar.run {
            animate().run {
                translationY(-toolbar.bottom.toFloat()).start()
            }
        }
        super.onWindowFocusChanged(hasFocus)

        if (hasFocus) {
            Log.println(Log.ERROR,"${asd.height}","${asd.width}magic")
            Log.println(Log.ERROR,"${asd.measuredHeight}","${asd.measuredWidth}magic")
            //val bm=Bitmap.createBitmap(asd.width,asd.height,Bitmap.Config.ARGB_8888)
            val drawer= FunctionDrawer(asd)
            drawer.drawFunction({x,y-> Math.sin(x * y) },-4.0,4.0,-4.0, 4.0)


         //   drawer.triangle(Point(0,0),Point(0,500),Point(500,0))
//            drawer.triangle(
//                Point(0,35),
//                Point(0,asd.height),
//                Point(asd.width,35),
//                Color.GREEN
//            )
//            drawer.rectangle(
//                Point(50,500),
//                Point(50,800),
//                Point(500,800),
//                Point(500,500),
//                Color.RED
//            )
            //drawer.test()
            //drawer.drawFunction({x,y-> Math.sin(x + y) },-4.0,4.0,-4.0, 4.0)

        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {

        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
//            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                asd.requestLayout()
//                val drawer= FunctionDrawer(asd)
//                drawer.drawFunction({x,y-> Math.sin(x * y) },-4.0,4.0,-4.0, 4.0)
//                Log.println(Log.ERROR,"${asd.height}","${asd.measuredWidth}magic")
//            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
//                asd.requestLayout()
//                val drawer= FunctionDrawer(asd)
//
//                drawer.drawFunction({x,y-> Math.sin(x * y) },-4.0,4.0,-4.0, 4.0)
//                Log.println(Log.ERROR,"${asd.height}","${asd.width}magic")
//            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
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
}
