package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

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
}