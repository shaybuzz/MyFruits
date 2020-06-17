package com.tut.mywebsocket.view.adapters

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:colorBackground")
    fun updateColor(view: View, color: String?) {
        color?.let {
            val color = Color.parseColor(color)
            view.setBackgroundColor(color)
        }
    }
}