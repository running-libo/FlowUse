package com.example.flowuse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        FlowCreate().main()

//        FlowCancel().main()

//        FlowMiddle().map()
//        FlowMiddle().transform()
//        FlowMiddle().onEach()
//        FlowMiddle().filter()
//        FlowMiddle().take()
//        FlowMiddle().zip()
//        FlowMiddle().flattenConcat()
//        FlowMiddle().flatMapLatest()


//        FlowTerminal().reduce()
//        FlowTerminal().fold()
//        FlowTerminal().launchIn()

//        FlowCode().code()
//        FlowCode().flowCold()
//        FlowCancel().cancelFlow()
//        FlowCode().influence()
//        FlowCode().onStartCompletion()

//        FlowThread().dispatcher()
        FlowThread().recieveData()

//        stateFlowTest()
//        stateFlowTest2()

//        SharedFlow().main()
    }

    private fun stateFlowTest() = runBlocking {
        val stateFlow = StateFlow()
        stateFlow.getApi(this) //开始获取结果

        launch(Dispatchers.IO) {
            stateFlow.state.collect {
                Log.i("minfo", "${Thread.currentThread().name} + $it")
            }
        }

        launch(Dispatchers.IO) {
            stateFlow.state.collect {
                Log.i("minfo", "${Thread.currentThread().name} + $it")
            }
        }
    }

    private fun stateFlowTest2() = runBlocking<Unit> {
        val stateFlow: StateFlow = StateFlow()

        stateFlow.getApi2(this) // 开始获取结果
        delay(1000)
        stateFlow.getApi3(this) // 开始获取结果

        val job1 = launch(Dispatchers.IO) {
            delay(8000)
            stateFlow.state.collect {
                Log.i("minfo", "${Thread.currentThread().name} + $it")
            }
        }
        val job2 = launch(Dispatchers.IO) {
            delay(8000)
            stateFlow.state.collect {
                Log.i("minfo", "${Thread.currentThread().name} + $it")
            }
        }

        // 避免任务泄漏，手动取消
        delay(10000)
        job1.cancel()
        job2.cancel()
    }

}