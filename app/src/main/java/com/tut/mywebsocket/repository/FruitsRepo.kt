package com.tut.mywebsocket.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tut.mywebsocket.model.FruitItem
import com.tut.mywebsocket.network.Network

class FruitsRepo {

    private val network = Network()
    private var currFilter: String = ""

    private var allFruits = mutableListOf<FruitItem>()

    private var _fruits: MutableLiveData<List<FruitItem>> = MutableLiveData()
    var fruits: LiveData<List<FruitItem>> = _fruits

    init {
        network.addListener(object : Network.FruitListener {
            override fun onFruit(fruitItem: FruitItem) {
                allFruits.add(0, fruitItem)
                updateWatchFruits()
            }
        })
    }

    fun updateWatchFruits() {
        var watchFruitList: List<FruitItem> = allFruits
        if (currFilter.isNotEmpty()) {
            watchFruitList = allFruits.filter {
                it.name.startsWith(currFilter)
            }
        }
        _fruits.postValue(watchFruitList)
    }

    fun startListen() {
        network.start()
    }

    fun stopListen() {
        network.stop()
    }

    fun filter(prefix: String) {
        currFilter = prefix
    }
}