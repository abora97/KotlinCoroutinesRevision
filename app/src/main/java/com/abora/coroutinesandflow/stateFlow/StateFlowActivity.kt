package com.abora.coroutinesandflow.stateFlow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.abora.coroutinesandflow.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StateFlowActivity : AppCompatActivity() {

    private lateinit var timerViewModel: TimerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_state_flow)

        timerViewModel= ViewModelProvider(this)[TimerViewModel::class.java]

        val textView:TextView=findViewById(R.id.textView)

        timerViewModel.stateTimeStateFlow()


        //LiveData
        timerViewModel.timerLiveData.observe(this){
            textView.text=it.toString()
            Log.d("here " , it.toString())
        }

        //StateFlow
        // launchWhenStarted to can lifeCycleAware and run when started
        lifecycleScope.launchWhenStarted{
            timerViewModel.timerStateFlow.collect{
                textView.text=it.toString()
                Log.d("here " , it.toString())
            }
        }

    }
}