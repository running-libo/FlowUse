package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

/**
 * 末端操作符
 */
class FlowTerminal {

    fun reduce() = runBlocking {
        val sum = (1..5).asFlow().reduce { a, b ->
            a + b
        }
        Log.i("minfo", sum.toString())
    }

    fun fold() = runBlocking {
        val sum = (1..5).asFlow().fold(100) { a, b ->
            a + b
        }
        Log.i("minfo", sum.toString())
    }

    fun launchIn() = runBlocking {
        (1..5).asFlow()
            .onEach { delay(100) }
            .flowOn(Dispatchers.IO)
            .onEach { Log.i("minfo", it.toString()) }
            .launchIn(this)

        flowOf("one", "two", "three", "four", "five")
            .onEach { delay(200) }
            .flowOn(Dispatchers.IO)
            .onEach { Log.i("minfo", it.toString()) }
            .launchIn(this)
    }
}