package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

class FlowCode {

    private fun createFlow(): Flow<Int> = flow {
        Log.i("minfo", "flow started")
        delay(1000)
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
    }

    fun code() = runBlocking {
        val flow = createFlow()
        Log.i("minfo", "flow collect")
        flow.collect {
            Log.i("minfo", it.toString())
        }
        Log.i("minfo", "flow collect again")
        flow.collect {
            Log.i("minfo", it.toString())
        }
    }

    //流是连续的
    fun influence() = runBlocking {
        (1..5).asFlow()
            .filter {
                Log.i("minfo", "filter $it")
                it % 2 == 0
            }
            .map {
                Log.i("minfo", "map $it")
                "String $it"
            }
            .collect {
                Log.i("minfo", "collect $it")
            }
    }

    fun onStartCompletion() = runBlocking {
        (1..5).asFlow()
            .onEach { delay(200) }
            .onStart {
                Log.i("minfo", "onStart")
            }
            .onCompletion {
                Log.i("minfo", "onCompletion")
            }
            .collect {
                Log.i("minfo", "$it")
            }
    }
}