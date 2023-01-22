package com.abora.coroutinesandflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChannelsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_channels)

        val kotlinChannel = Channel<String>(2) // 2 is buffer to specific data length returned
        val charList = arrayOf("A", "B", "C", "D")

        runBlocking {
            launch {
                for (char in charList) {
                    // send for send data to channel and make suspend
//                    kotlinChannel.send(char)

                    // to send data but not suspend
                        // channel don't wait data
                        //receive one action
                    kotlinChannel.trySend(char).isSuccess
//                    delay(1000)
                }
            }

//            launch {
//                for (char in kotlinChannel) {
//                    Log.d("DataChannel", char)
////                    delay(2000)
//                }
//            }

            for (char in kotlinChannel) {
                Log.d("DataChannel", char)
            }
        }

    }
}