package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

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
}