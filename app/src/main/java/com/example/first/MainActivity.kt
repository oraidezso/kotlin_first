package com.example.first

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        var bm=Bitmap.createBitmap(50,100,Bitmap.Config.ARGB_8888)
//        Log.println(1,"${imageView.width.}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
//        Log.println(1,"${imageView.width}","asd")
//        Log.println(1,"${imageView.height}","asd")
        for (i in 0..imageView.width-1) {
            for (j in 0..imageView.height-1){
                bm.setPixel(Color.red(0),i,j)
            }

        }
        imageView.setImageBitmap(bm)
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
