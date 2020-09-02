package com.example.lint

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.UiThread
import androidx.annotation.WorkerThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val textView = TextView(this)
        textView.text = "Hello World"*/
        //longOperation()
    }


    /*@SuppressLint("SetTextI18n")
    private fun setText() {
        val textView = TextView(this)
        textView.text = "Hello World"
    }

    @WorkerThread
    fun longOperation() {

    }*/


}