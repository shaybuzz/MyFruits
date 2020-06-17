package com.tut.mywebsocket.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tut.mywebsocket.R
import com.tut.mywebsocket.databinding.FruitsLayoutBinding
import com.tut.mywebsocket.view.adapters.FruitsAdapter
import kotlinx.android.synthetic.main.fruits_layout.*


class MainActivity : AppCompatActivity() {

    lateinit var fruitsViewModel: FruitsViewModel
    lateinit var adapter:FruitsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: FruitsLayoutBinding =
            DataBindingUtil.setContentView(this, R.layout.fruits_layout)

        fruitsViewModel =
            ViewModelProvider(
                this,
                FruitsViewModelFactory(application)
            ).get(FruitsViewModel::class.java)

        fruitsViewModel.fruits.observe(this, Observer {
            adapter.submitBooks(it)
        })

        fruitsViewModel.filterName.observe(this, Observer {
            fruitsViewModel.filter(it)
        })

        adapter = FruitsAdapter()
        list.adapter = adapter

      binding.viewmodel = fruitsViewModel



    }
}