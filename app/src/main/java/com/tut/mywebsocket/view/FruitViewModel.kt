package com.tut.mywebsocket.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tut.mywebsocket.repository.FruitsRepo

class FruitsViewModel(application: Application) : AndroidViewModel(application) {
    private val fruitsRepo = FruitsRepo()
    val fruits = fruitsRepo.fruits

    val filterName: MutableLiveData<String> = MutableLiveData()

    fun onStart() {
        fruitsRepo.startListen()
    }

    fun onStop() {
        fruitsRepo.stopListen()
    }

    fun filter(name: String) {
        fruitsRepo.filter(name)
    }

}

@Suppress("UNCHECKED_CAST")
class FruitsViewModelFactory(val application: Application) :
    ViewModelProvider.AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FruitsViewModel::class.java)) {
            return FruitsViewModel(application) as T
        }
        throw IllegalArgumentException()
    }

}