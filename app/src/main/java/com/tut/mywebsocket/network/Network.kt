package com.tut.mywebsocket.network

import com.google.gson.Gson
import com.tut.mywebsocket.model.FruitItem
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class Network {

    private val URL = "ws://superdo-groceries.herokuapp.com/receive"
    private val client: OkHttpClient = OkHttpClient()
    private var listen = false
    private var fruitListener: Network.FruitListener? = null

    interface FruitListener {
        fun onFruit(fruitItem: FruitItem)

    }

    init {
        val request: Request =
            Request.Builder().url(URL).build()
        val ws = client.newWebSocket(request, object : WebSocketListener() {

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                if (listen) {
                    val fruit: FruitItem = Gson().fromJson(text, FruitItem::class.java)
                    fruitListener?.onFruit(fruit)
                }
            }
        })
        client.dispatcher.executorService.shutdown()
    }

    fun addListener(theFruitListener: FruitListener) {
        fruitListener = theFruitListener
    }

    fun start() {
        listen = true
    }

    fun stop() {
        listen = false
    }
}