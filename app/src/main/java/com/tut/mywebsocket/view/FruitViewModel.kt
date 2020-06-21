package com.tut.mywebsocket.view

import android.app.Application
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.tut.mywebsocket.repository.FruitsRepo

class FruitsViewModel(application: Application) : AndroidViewModel(application) {
    private val fruitsRepo = FruitsRepo()
    val fruits = fruitsRepo.fruits
    val filterName = ObservableField<String>()
    private val propertyCallBack = object :Observable.OnPropertyChangedCallback(){
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            sender?.let {
                (sender as ObservableField<String>).get()?.let {prefix->
                    fruitsRepo.filter(prefix)
                }
            }
        }
    }

    init {
        filterName.addOnPropertyChangedCallback(propertyCallBack)
    }

    override fun onCleared() {
        super.onCleared()
        filterName.removeOnPropertyChangedCallback(propertyCallBack)
    }


    fun onStart() {
        fruitsRepo.startListen()
    }

    fun onStop() {
        fruitsRepo.stopListen()
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