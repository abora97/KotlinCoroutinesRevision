package com.abora.coroutinesandflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class StructuredConcurrencyActivity : AppCompatActivity() {

    private val parentJop: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_structured_concurrency)

        firstExample()
        secondExample()

    }


    private fun secondExample() {

        val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO + parentJop)

        coroutineScope.launch {
            val childFromNetwork = launch { getUserFromNetwork() }
            val childFromDataBase = launch { getUserFromDataBase() }
        }

    }

    private fun firstExample() {
        val parentJop: Job = Job()

        // when use job when network call fail get data from database is fail The other one
        val job: Job = GlobalScope.launch(parentJop) {
            val childFromNetwork = launch { getUserFromNetwork() }
            val childFromDataBase = launch { getUserFromDataBase() }

            // to make lunch 3 work when child 1 and 2 finish
            childFromNetwork.join()
            childFromDataBase.join()
            // or
            joinAll(childFromNetwork, childFromDataBase)

            // make child 1 finish and make cancel to job
            childFromNetwork.cancelAndJoin()

            launch { delay(2000) }
        }
//        parentJop.cancel()
//        job.cancel() // to cancel parent and all childes
    }

    private suspend fun getUserFromNetwork(): String {
        delay(2000)
        return "abora"
    }

    private suspend fun getUserFromDataBase(): String {
        delay(2000)
        return "abora"
    }

    override fun onStop() {
        super.onStop()
        parentJop.cancel()
    }
}
