package com.abora.coroutinesandflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class FlowsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flows)
//        example1()
        example2()


    }

    private fun example2() {
        runBlocking {
            // producer
            val flow1 = flow<Int> {
                for (i in 1..3) {
                    emit(i) // suspend fun
                    delay(1000)
                }
            }


        val flow2 = flow<String> {
            val list = listOf<String>("A","B","C")
                for (i in list) {
                    emit(i) // suspend fun
                    delay(2000)
                }
            }

            flow1.zip(flow2){a, b-> "$a:$b"

            }.collect{
                Log.d("here",it)
            }

        }
    }

    private fun example1() {
        runBlocking {
            // producer
            flow<Int> {
                for (i in 1..10) {
                    emit(i) // suspend fun
                    Log.d("here producer", i.toString())
                    delay(1000)
                }
                //intermediate
            }.filter { i ->
                i < 5
            }   //collector
                //flow don't work without add collector
                .buffer().collect {
                    Log.d("here collect", it.toString())
                    delay(2000)
                }
        }
    }
}