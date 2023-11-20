package com.example.flowuse

import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect

class SharedFlow {

    private val _state = MutableSharedFlow<Int>(replay = 3,
        extraBufferCapacity = 2)
    val state: MutableSharedFlow<Int> get() = _state

    fun getApi(scope: CoroutineScope) {
        scope.launch {
            for (i in 0..20) {
                delay(200)
                _state.emit(i)
                Log.i("minfo", "send data $i")
            }
        }
    }

    fun main() = runBlocking {
        getApi(this)

        val job = launch(Dispatchers.IO) {
            delay(3000)
            state.collect {
                Log.i("minfo", "collect  $it")
            }
        }
        delay(5000)
        job.cancel()
    }
}