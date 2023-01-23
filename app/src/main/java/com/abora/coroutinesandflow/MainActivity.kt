package com.abora.coroutinesandflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    lateinit var myTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this,FlowsActivity::class.java))
//        startActivity(Intent(this,ChannelsActivity::class.java))
        finish()
//        startActivity(Intent(this,StructuredConcurrencyActivity::class.java))
        myTextView = findViewById(R.id.tvMyText)

        Log.d("MainActivity ", "main thread")
//        runBlocking {
//            myTextView.text = " from runBlocking"
//            printMyTextAfterDelay("Abora")
//        }

        Log.d("MainActivity ", "back to main thread")

        GlobalScope.launch {
            Log.d("myThread -> ", Thread.currentThread().name)
            printMyTextAfterDelayParallel("Abora")
            printMyTextAfterDelayParallel("Abora2")
        }
    }

    suspend fun printMyTextAfterDelay(myText: String) {
        delay(2000)
        withContext(Dispatchers.Main) {
            myTextView.text = myText
        }
        Log.d("my fun", myText)
    }

    suspend fun printMyTextAfterDelayParallel(myText: String) {
       withContext(Dispatchers.IO){
            delay(2000)
            Log.d("my fun", myText)
        }
    }
}