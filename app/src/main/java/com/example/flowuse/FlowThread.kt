package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.coroutines.coroutineContext

class FlowThread {

    fun dispatcher() = runBlocking {
        val mDispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

        (1..5).asFlow()
            .onEach {
                //生产数据
                Log.i("minfo", "${Thread.currentThread().name} + product: $it")
            }.flowOn(Dispatchers.IO)
            .map {
                //转换数据
                Log.i("minfo", "${Thread.currentThread().name} + $it to String")
                "String: $it"
            }.flowOn(mDispatcher)
            .onCompletion {
                mDispatcher.close()
            }
            .collect {
                //消费数据
                Log.i("minfo", "${Thread.currentThread().name} + collect: + $it")
            }
    }

    //在主线程发送数据，切换协程上下文，在子线程去接收数据
    val flow = flow {
        withContext(Dispatchers.IO) {
            val data = "Data"
            emit(data)
        }
    }

    fun recieveData() {
        runBlocking {
            flow.flowOn(Dispatchers.Main)
                .collect { data->
                    println("$data recieved")
                }
        }
    }
}