package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Flow的取消
 */
class FlowCancel {

    fun simple(): Flow<Int> = flow {
        for (i in 1..3) {
            delay(100)
            println("Emitting $i")
            emit(i)
        }
    }

    fun main() = runBlocking {
        withTimeoutOrNull(250) {
            simple().collect {
                Log.i("minfo", it.toString())
            }
        }
        Log.i("minfo", "Done")
    }

    //消费者只处理一次数据即取消
    val flow = flow {
        for (i in 1..3) {
            delay(100)
            emit("data:" + i)
        }
    }

    fun cancelFlow() = runBlocking {
        flow.collect { data->
            println("收到了:" + data)
            cancel()  //取消flow在当前协程中调用cancel()即可
        }
    }
}