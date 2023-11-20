package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

/**
 * Flow创建方式
 */
class FlowCreate {

    private fun createFlow(): Flow<Int> = flow {
        delay(1000)
        emit(1)
        delay(1000)
        emit(2)
        delay(1000)
        emit(3)
    }

    //flowOf() 构建器定义了一个发射固定值集的流, 使用 flowOf 构建 Flow 不需要显示调用 emit() 发射数据
    //因为在扩展函数内部使用了emit
    private fun createFlow2(): Flow<Int> = flowOf(1, 2, 3)

    //使用 asFlow() 扩展函数，可以将各种集合与序列转换为流，也不需要显示调用 emit() 发射数据
    //因为在扩展函数内部使用了emit
    private fun createFlow3(): Flow<Int> = listOf(1, 2, 3).asFlow()

    fun main() = runBlocking {
        createFlow().collect {
            Log.i("minfo", it.toString())
        }

        createFlow2().collect {
            Log.i("minfo", it.toString())
        }

        createFlow3().collect {
            Log.i("minfo", it.toString())
        }
    }
}