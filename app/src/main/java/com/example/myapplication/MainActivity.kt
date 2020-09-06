package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.os.postDelayed
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var mainHandler: Handler

    private val updateTextTask = object: Runnable {
        override fun run() {
            Log.d("callRun","Every Second call")
            mainHandler.postDelayed(this,10000)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      //  mainHandler=Handler(Looper.getMainLooper())
        buttonCall.setOnClickListener {
            repeatNetworkCall()
        }
    }

    override fun onResume() {
        super.onResume()
       // mainHandler.post(updateTextTask)
    }

    fun repeatNetworkCall(){
        lifecycleScope.launch {
            while(isActive){
                delay(1000L)
                Log.d("callRun","Still running ... ")
            }
        }

        GlobalScope.launch {
            delay(5000L)
            Intent(this@MainActivity,MainActivity2::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
//        mainHandler.removeCallbacks(updateTextTask)

    }


}