package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * Flow 中间转换操作符
 */
class FlowMiddle {

    fun map() = runBlocking {
        (1..5).asFlow().map { "string $it" }
            .collect {
                Log.i("minfo", it)
            }
    }

    fun transform() = runBlocking {
        (1..5).asFlow().transform {
            emit(it * 2)
            delay(1000)
            emit("String : $it")
        }.collect {
            Log.i("minfo", "" + it)
        }
    }

    fun onEach() = runBlocking {
        (1..5).asFlow()
            .onEach {
                Log.i("minfo", "onEach $it")
            }.collect {
                Log.i("minfo", "$it")
            }
    }

    fun filter() = runBlocking {
        (1..5).asFlow()
            .filter { it % 2 == 0 }
            .collect {
                Log.i("minfo", "$it")
            }
    }

    fun take() = runBlocking {
        (1..5).asFlow()
            .take(2).collect {
                Log.i("minfo", "$it")
            }
    }

    fun zip() = runBlocking {
        val flowA = (1..5).asFlow()
        val flowB = flowOf("one" , "two" , "three" , "four", "five")
        flowA.zip(flowB) { a, b ->
            "$a -- $b"
        }.collect {
            Log.i("minfo", "$it")
        }
    }

    fun flattenConcat() = runBlocking {
        val flowA = (1..5).asFlow().onEach { delay(1000) }
        val flowB = flowOf("one", "two", "three","four","five").onEach { delay(1000) }
        flowOf(flowA, flowB).flattenConcat()
            .collect {
                Log.i("minfo", "$it")
            }
    }

    fun flatMapLatest() = runBlocking {
        (1..5).asFlow().onEach { delay(100) }
            .flatMapLatest {
                flow {
                    Log.i("minfo", "begin flatMapLatest $it")
                    delay(200)
                    emit("String $it")
                    Log.i("minfo", "end flatMapLatest $it")
                }
            }.collect {
                Log.i("minfo", "$it")
            }
    }

}