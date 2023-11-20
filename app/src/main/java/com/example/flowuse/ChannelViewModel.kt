package com.example.flowuse

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

//viewModel中
class ChannelViewModel {

    private val _loadingChannel = Channel<Boolean>()
    val loadingFlow = _loadingChannel.receiveAsFlow()

    private suspend fun loadStart() {
        _loadingChannel.send(true)
    }

    private suspend fun loadFinish() {
        _loadingChannel.send(false)
    }

}