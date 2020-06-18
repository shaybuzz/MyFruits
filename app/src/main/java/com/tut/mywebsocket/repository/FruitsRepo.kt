package com.tut.mywebsocket.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tut.mywebsocket.model.FruitItem
import com.tut.mywebsocket.network.Network

class FruitsRepo {

    private val network = Network()
    private var currFilter: String = ""
    private val _fruits: MutableLiveData<MutableList<FruitItem>> =
        MutableLiveData<MutableList<FruitItem>>()
    val fruits: LiveData<MutableList<FruitItem>> = _fruits

    init {
        network.addListener(object : Network.FruitListener {
            override fun onFruit(fruitItem: FruitItem) {
                val fruits = _fruits.value.orEmpty().toMutableList()
                if (currFilter == "" || fruitItem.name.startsWith(currFilter)) {
                    fruits.add(0, fruitItem)
                    _fruits.postValue(fruits)
                }
            }
        })
    }

    fun startListen() {
        network.start()
    }

    fun stopListen() {
        network.stop()
    }

    fun filter(name: String) {
        currFilter = name
        if (!name.isEmpty()) {
            val newList = _fruits.value?.filter {
                it.name.startsWith(name)
            }
            newList?.let {
                _fruits.postValue(it as MutableList<FruitItem>?)
            }
        }
    }
}