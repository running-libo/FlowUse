package com.example.flowuse

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow

class StateFlow {

    private val _state = MutableStateFlow<String>("unKnown")
    val state: kotlinx.coroutines.flow.StateFlow<String> get() = _state

    fun getApi(scope: CoroutineScope) {
        scope.launch {
            var res = getApi()
            _state.value = res
        }
    }

    fun getApi2(scope: CoroutineScope) {
        scope.launch {
            delay(2000)
            _state.value = "hello, coroutine"
        }
    }

    fun getApi3(scope: CoroutineScope) {
        scope.launch {
            delay(2000)
            _state.value = "hello, kotlin"
        }
    }

    /**
     * 进行网络Api请求
     */
    private suspend fun getApi() = withContext(Dispatchers.IO) {
        delay(2000) //模拟耗时请求
        "result"
    }
}