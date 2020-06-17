package com.tut.mywebsocket.network

import com.google.gson.Gson
import com.tut.mywebsocket.model.FruitItem
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class Network {

    interface FruitListener {
        fun onFruit(fruitItem: FruitItem)

    }

    private var fruitListener: Network.FruitListener? = null


    fun addListener(theFruitListener: FruitListener) {
        fruitListener = theFruitListener
    }

    private val client: OkHttpClient = OkHttpClient()
    private var listen = false

    init {
        val request: Request =
            Request.Builder().url("ws://superdo-groceries.herokuapp.com/receive").build()
        val ws = client.newWebSocket(request, object : WebSocketListener() {

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                if (listen) {
                    val fruit = Gson().fromJson<FruitItem>(text, FruitItem::class.java)
                    fruitListener?.onFruit(fruit)
                    // Log.d("###", "### fruit $fruit")
                }


            }

        })
        client.dispatcher.executorService.shutdown()
    }


    fun start() {
        listen = true
    }

    fun stop() {
        listen = false
    }

}